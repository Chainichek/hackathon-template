package com.munsun.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginPasswordDto(
        @NotBlank
        String login,

        @NotBlank
        @Size(min = 8, max = 16)
        String password
) {}