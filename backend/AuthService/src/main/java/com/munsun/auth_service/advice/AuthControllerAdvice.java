package com.munsun.auth_service.advice;

import com.munsun.auth_service.dto.response.ErrorMessageDto;
import com.munsun.auth_service.exceptions.AccountNotFoundException;
import com.munsun.auth_service.exceptions.SaveAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class AuthControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessageDto(message));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> handleAccountNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessageDto(e.getMessage()));
    }

    @ExceptionHandler(SaveAccountException.class)
    public ResponseEntity<ErrorMessageDto> handleSaveAccountException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessageDto(e.getMessage()));
    }
}
