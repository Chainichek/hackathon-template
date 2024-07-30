package ru.babim.lib.jwtprovider.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface JwtProvider {
    AbstractAuthenticationToken getAuthentication(String token);
}
