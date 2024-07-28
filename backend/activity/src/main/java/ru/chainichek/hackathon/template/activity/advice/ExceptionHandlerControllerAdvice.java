package ru.chainichek.hackathon.template.activity.advice;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import ru.chainichek.hackathon.template.activity.dto.util.ErrorMessage;
import ru.chainichek.hackathon.template.activity.dto.util.InternalErrorMessage;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException exception,
                                                                  HttpServletRequest request) {
        final ErrorMessage message = new ErrorMessage(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI());

        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorMessage> handlerMethodValidationException(HandlerMethodValidationException exception,
                                                                         HttpServletRequest request) {
        final List<String> errors = exception.getAllValidationResults().stream()
                .flatMap(result -> result.getResolvableErrors().stream())
                .filter(messageError -> messageError instanceof ObjectError)
                .map(messageError -> {
                    if (messageError instanceof FieldError fieldError) {
                        return "%s = %s: %s".formatted(
                                fieldError.getField(),
                                fieldError.getRejectedValue(),
                                fieldError.getDefaultMessage());
                    }

                    return messageError.getDefaultMessage();
                })
                .toList();

        final ErrorMessage message = new ErrorMessage(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value(),
                errors.toString(),
                request.getRequestURI());

        log.error("%s: %s".formatted(exception.getMessage(), errors.toString()), exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<InternalErrorMessage> otherException(RuntimeException exception,
                                                               HttpServletRequest request) {
        final InternalErrorMessage message = new InternalErrorMessage(LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toArray(String[]::new),
                request.getRequestURI());

        log.error(exception.getMessage(), exception);
        log.warn("Unexpected exception: %s".formatted(exception));

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(message);
    }
}
