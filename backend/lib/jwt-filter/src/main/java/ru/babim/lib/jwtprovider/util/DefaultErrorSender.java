package ru.babim.lib.jwtprovider.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import ru.babim.lib.jwtprovider.exception.UnauthorizedException;

import java.io.IOException;

public class DefaultErrorSender implements ErrorSender {
    @Override
    public void sendUnauthorized(UnauthorizedException exception, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write("Unauthorized".getBytes());
    }

    @Override
    public void sendBadCredentials(BadCredentialsException exception, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write("BadCredentials".getBytes());
    }
}
