package ru.babim.template.employee.dto;

import ru.babim.template.employee.dto.request.enums.Role;

public record EmployeeInfoDto(
        String login,
        String password,
        String name,
        String lastname,
        String email,
        Role role
) {}