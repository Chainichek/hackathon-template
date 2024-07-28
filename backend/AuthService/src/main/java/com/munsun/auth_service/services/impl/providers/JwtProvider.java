package com.munsun.auth_service.services.impl.providers;

import com.auth0.jwt.algorithms.Algorithm;
import com.munsun.auth_service.dto.response.JwtTokenDto;
import com.munsun.auth_service.models.Account;

import java.util.Date;

public interface JwtProvider {
    String generate(Account user, Date date);
    JwtTokenDto getJwtTokenDto(Account account);

    Algorithm getAlgorithm();
}
