package ru.babim.lib.jwtprovider.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import ru.babim.lib.jwtprovider.exception.UnauthorizedException;

import java.io.IOException;

public interface ErrorSender {
    void sendUnauthorized(UnauthorizedException exception, HttpServletResponse response) throws IOException;
    void sendBadCredentials(BadCredentialsException exception, HttpServletResponse response) throws IOException;
}
