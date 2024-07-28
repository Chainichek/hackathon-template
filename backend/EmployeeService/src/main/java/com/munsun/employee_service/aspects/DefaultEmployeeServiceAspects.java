package com.munsun.employee_service.aspects;

import com.munsun.employee_service.exceptions.CreateEmployeeException;
import feign.FeignException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DefaultEmployeeServiceAspects {
    @Pointcut("execution(* com.munsun.employee_service.services.EmployeeService+.createEmployee(..))")
    private void executionCreateEmployeeMethod(){}

    @AfterThrowing(value = "executionCreateEmployeeMethod()", throwing = "e")
    public void loggingThrownFeignException(FeignException e) {
        if(e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            throw new CreateEmployeeException(e.contentUTF8());
        }
    }
}
