package ru.babim.lib.logger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Loggable(invokeMessage = "Message listened on \"{}\"",
        invokeParametersMessage = "Got: {}",
        executeMessage = "Message listening ended on \"{}\"",
        executeParametersMessage = "Return: {}",
        delimiter = "; ")
public @interface ConsumerLoggable {
}
