package com.munsun.employee_service.dto.response;

import java.util.UUID;

public record AccountPersistentDto(
        UUID accountId,
        String login,
        String password,
        String role
) {}