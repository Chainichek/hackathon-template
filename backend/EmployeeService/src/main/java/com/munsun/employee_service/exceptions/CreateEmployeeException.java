package com.munsun.employee_service.exceptions;

public class CreateEmployeeException extends RuntimeException {
    public CreateEmployeeException(String message) {
        super(message);
    }
}
