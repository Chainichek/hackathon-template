package com.munsun.auth_service.dto.request;

import com.munsun.auth_service.models.enums.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserInfoDto(
        @NotBlank
        String login,
        @NotBlank
        @Size(min = 8, max = 16)
        String password,
        @NotNull
        Role role,

        @NotBlank
        String name,

        @NotBlank
        String lastname,

        @Min(18)
        Integer age,

        @NotBlank
        String email,

        @NotBlank
        String phoneNumber
) {}