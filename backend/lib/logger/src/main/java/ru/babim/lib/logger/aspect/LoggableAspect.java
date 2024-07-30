package ru.babim.lib.logger.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import ru.babim.lib.logger.annotation.ConsumerLoggable;
import ru.babim.lib.logger.annotation.ControllerLoggable;
import ru.babim.lib.logger.annotation.Loggable;
import ru.babim.lib.logger.annotation.ProducerLoggable;
import ru.babim.lib.logger.annotation.TransactionLoggable;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
public class LoggableAspect {
    private static final List<Class<? extends Annotation>> LOGGABLE_ANNOTATIONS = List.of(
            Loggable.class,
            TransactionLoggable.class,
            ControllerLoggable.class,
            ConsumerLoggable.class,
            ProducerLoggable.class);

    @Pointcut("""
            within(@ru.babim.lib.logger.annotation.Loggable *)
            || within(@ru.babim.lib.logger.annotation.ControllerLoggable *)
            || within(@ru.babim.lib.logger.annotation.TransactionLoggable *)
            || within(@ru.babim.lib.logger.annotation.ConsumerLoggable *)
            || within(@ru.babim.lib.logger.annotation.ProducerLoggable *)
            """)
    public void loggableTarget() {}

    @Pointcut("""
            @annotation(ru.babim.lib.logger.annotation.Loggable)
            || @annotation(ru.babim.lib.logger.annotation.ControllerLoggable)
            || @annotation(ru.babim.lib.logger.annotation.TransactionLoggable)
            || @annotation(ru.babim.lib.logger.annotation.ConsumerLoggable)
            || @annotation(ru.babim.lib.logger.annotation.ProducerLoggable)
            """)
    public void loggableMethod() {}

    @Pointcut("""
            execution(public * * (..))
            """)
    public void publicMethod() {}

    @Around("(loggableTarget() || loggableMethod()) && publicMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        final Class<?> targetClass = joinPoint.getTarget().getClass();
        final Loggable loggable = getEffectiveLoggable(
                method,
                targetClass);
        final String methodName = getMethodName(method);

        Object[] args = null;
        Logger log = null;


        if (loggable != null) {
            log = LoggerFactory.getLogger(targetClass);

            if (loggable.value() == null) {
                throw new NullPointerException("No logging level found");
            }

            if (loggable.showArgs()) {
                args = joinPoint.getArgs();
            }

            if (args == null || args.length == 0) {
                log(log, loggable.value(), loggable.invokeMessage(), methodName);
            } else {
                log(log, loggable.value(), "%s%s%s".formatted(loggable.invokeMessage(),
                        loggable.delimiter(),
                        loggable.invokeParametersMessage()), methodName, args);
            }
        }

        final Object result = joinPoint.proceed();

        if (loggable != null) {
            if (!loggable.showReturn() || result == null) {
                log(log, loggable.value(), loggable.executeMessage(), methodName);
            } else {
                log(log, loggable.value(), "%s%s%s".formatted(loggable.executeMessage(),
                        loggable.delimiter(),
                        loggable.executeParametersMessage()), methodName, result);
            }
        }
        return result;
    }

    private Loggable getEffectiveLoggable(Method method, Class<?> targetClass) {
        final Loggable loggable = getLoggableFromAnnotations(method);
        return loggable == null ? getLoggableFromAnnotations(targetClass) : loggable;
    }

    private Loggable getLoggableFromAnnotations(AnnotatedElement element) {
        for (Class<? extends Annotation> annotationClass : LOGGABLE_ANNOTATIONS) {
            if (element.isAnnotationPresent(annotationClass)) {
                if (annotationClass == Loggable.class) {
                    return element.getAnnotation(Loggable.class);
                } else {
                    return annotationClass.cast(element.getAnnotation(annotationClass))
                            .annotationType()
                            .getAnnotation(Loggable.class);
                }
            }
        }
        return null;
    }

    private String getMethodName(Method method) {
        final String name = method.getName();
        return name.replaceAll(String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ), " "
        ).toLowerCase();
    }

    private void log(Logger log,
                     Level level,
                     String message,
                     Object... args) {
        if (log == null) {
            throw new NullPointerException("No logger found");
        }

        switch (level) {
            case TRACE -> log.trace(message, args);
            case DEBUG -> log.debug(message, args);
            case INFO -> log.info(message, args);
            case WARN -> log.warn(message, args);
            case ERROR -> log.error(message, args);
        }
    }
}
