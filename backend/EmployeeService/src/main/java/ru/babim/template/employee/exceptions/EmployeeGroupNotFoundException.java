package ru.babim.template.employee.exceptions;

public class EmployeeGroupNotFoundException extends RuntimeException{
    public EmployeeGroupNotFoundException(String format) {
        super(format);
    }
}
