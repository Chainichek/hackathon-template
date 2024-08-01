package com.munsun.employee_service.queries.payload;

public record EmployeeCreationMessage(
        String email,
        String name,
        String lastname
) {}