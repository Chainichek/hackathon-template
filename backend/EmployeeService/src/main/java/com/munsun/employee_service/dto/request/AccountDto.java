package com.munsun.employee_service.dto.request;

public record AccountDto(
        String login,
        String password
) {}