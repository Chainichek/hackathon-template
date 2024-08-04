package ru.babim.template.employee.aspects;

import feign.FeignException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.babim.template.employee.exceptions.CreateEmployeeException;

@Aspect
@Component
public class DefaultEmployeeServiceAspects {
    @Pointcut("execution(* ru.babim.template.employee.services.EmployeeService+.createEmployee(..))")
    private void executionCreateEmployeeMethod(){}

    @AfterThrowing(value = "executionCreateEmployeeMethod()", throwing = "e")
    public void loggingThrownFeignException(FeignException e) {
        if(e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            throw new CreateEmployeeException(e.contentUTF8());
        }
    }
}
