package ru.practicum.exception;

public class InvalidTimestampException extends RuntimeException {
    public InvalidTimestampException(String message) {
        super(message);
    }
}
