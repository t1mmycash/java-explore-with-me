package ru.practicum.ewm.exception;

public class EntityCannotBeDeletedException extends RuntimeException{
    public EntityCannotBeDeletedException(String message) {
        super(message);
    }
}
