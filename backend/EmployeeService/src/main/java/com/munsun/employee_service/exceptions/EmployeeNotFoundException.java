package com.munsun.employee_service.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String format) {
        super(format);
    }
}
