package ru.babim.template.employee.dto.response;

import java.util.UUID;

public record AccountPersistentDto(
        UUID accountId,
        String login,
        String password,
        String role
) {}