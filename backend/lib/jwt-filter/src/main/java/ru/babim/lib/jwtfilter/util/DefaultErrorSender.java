package ru.babim.lib.jwtfilter.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import ru.babim.lib.jwtfilter.exception.UnauthorizedException;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class DefaultErrorSender implements ErrorSender {
    private final ObjectMapper objectMapper;

    @Override
    public void sendUnauthorized(UnauthorizedException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final ErrorMessage message = new ErrorMessage(LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                HttpStatus.UNAUTHORIZED.value(),
                exception.getMessage(),
                request.getRequestURI());

        log.error(exception.getMessage(), exception);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(message));
    }

    @Override
    public void sendBadCredentials(BadCredentialsException exception, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final ErrorMessage message = new ErrorMessage(LocalDateTime.now(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                HttpStatus.FORBIDDEN.value(),
                exception.getMessage(),
                request.getRequestURI());

        log.error(exception.getMessage(), exception);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(objectMapper.writeValueAsBytes(message));
    }
}
