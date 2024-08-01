package com.munsun.auth_service.exceptions;

public class SaveAccountException extends RuntimeException {
    public SaveAccountException(String text) {
        super(text);
    }
}
