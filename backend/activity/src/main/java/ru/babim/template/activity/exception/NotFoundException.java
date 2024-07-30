package ru.babim.template.activity.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message, String id) {
        super("%s: id = %s".formatted(message, id));
    }
}
