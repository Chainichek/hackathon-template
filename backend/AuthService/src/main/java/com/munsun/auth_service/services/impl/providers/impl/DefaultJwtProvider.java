package com.munsun.auth_service.services.impl.providers.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.munsun.auth_service.dto.response.JwtTokenDto;
import com.munsun.auth_service.models.Account;
import com.munsun.auth_service.services.impl.providers.JwtProvider;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
public class DefaultJwtProvider implements JwtProvider {
    @Value("${spring.application.name}")
    private String issuerName;

    @Value("${security.token.secret}")
    private String secretKey;

    @Value("${security.token.expire}")
    private Long expire;

    private Algorithm algorithm;

    @PostConstruct
    public void init() {
        algorithm = Algorithm.HMAC256(secretKey);
    }

    @Override
    public JwtTokenDto getJwtTokenDto(Account account) {
        Date now = new Date();
        return new JwtTokenDto(generate(account, now), new Date(now.getTime()+expire).toString());
    }

    @Override
    public String generate(Account account, Date now) {
        return JWT.create()
                .withIssuer(issuerName)
                .withClaim("login", account.getLogin())
                .withClaim("role", account.getRole().name())
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime()+expire))
                .sign(algorithm);
    }
}