package com.munsun.auth_service.dto.response;

public record JwtTokenDto(
        String access,
        String expired
) {}