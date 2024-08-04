package ru.babim.template.employee.queries.payload;

public record EmployeeCreationMessage(
        String email,
        String name,
        String lastname
) {}