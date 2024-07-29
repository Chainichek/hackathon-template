package com.munsun.employee_service.advice;

import com.munsun.employee_service.dto.response.ErrorMessage;
import com.munsun.employee_service.exceptions.CreateEmployeeException;
import com.munsun.employee_service.exceptions.EmployeeGroupNotFoundException;
import com.munsun.employee_service.exceptions.EmployeeNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeControllerAdvice {
    @ExceptionHandler({EmployeeNotFoundException.class, EmployeeGroupNotFoundException.class})
    public ResponseEntity<?> handleEmployeeNotFoundException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(CreateEmployeeException.class)
    public ResponseEntity<?> handleCreateEmployeeException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(message));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }
}