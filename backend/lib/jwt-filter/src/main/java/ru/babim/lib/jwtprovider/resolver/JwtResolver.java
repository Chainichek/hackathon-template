package ru.babim.lib.jwtprovider.resolver;

import org.springframework.security.authentication.BadCredentialsException;

public interface JwtResolver {
    void resolve(String authToken) throws BadCredentialsException;
}
