package ru.babim.lib.jwtfilter.provider;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public interface JwtProvider {
    AbstractAuthenticationToken getAuthentication(String token);
}
