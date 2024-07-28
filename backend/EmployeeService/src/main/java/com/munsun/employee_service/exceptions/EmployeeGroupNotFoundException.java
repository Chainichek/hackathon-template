package com.munsun.employee_service.exceptions;

public class EmployeeGroupNotFoundException extends RuntimeException{
    public EmployeeGroupNotFoundException(String format) {
        super(format);
    }
}
