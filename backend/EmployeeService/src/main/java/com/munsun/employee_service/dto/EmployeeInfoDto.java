package com.munsun.employee_service.dto;

import com.munsun.employee_service.dto.request.enums.Role;

public record EmployeeInfoDto(
        String login,
        String password,
        String name,
        String lastname,
        String email,
        Role role
) {}