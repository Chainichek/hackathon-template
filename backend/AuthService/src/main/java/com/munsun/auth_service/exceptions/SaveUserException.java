package com.munsun.auth_service.exceptions;

public class SaveUserException extends RuntimeException {
    public SaveUserException(String message) {
        super(message);
    }
}
