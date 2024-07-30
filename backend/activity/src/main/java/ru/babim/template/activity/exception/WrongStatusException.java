package ru.babim.template.activity.exception;

public class WrongStatusException extends RuntimeException {
    public WrongStatusException(String message) {
        super(message);
    }
}
