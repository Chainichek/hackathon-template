package ru.babim.lib.jwtfilter.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import ru.babim.lib.jwtfilter.exception.UnauthorizedException;

import java.io.IOException;

public interface ErrorSender {
    void sendUnauthorized(UnauthorizedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException;
    void sendBadCredentials(BadCredentialsException exception, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
