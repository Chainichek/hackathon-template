package ru.babim.template.employee.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String format) {
        super(format);
    }
}
