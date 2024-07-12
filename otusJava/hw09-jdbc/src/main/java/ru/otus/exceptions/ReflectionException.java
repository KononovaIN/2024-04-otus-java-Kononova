package ru.otus.exceptions;

public class ReflectionException extends RuntimeException {
    public ReflectionException(Exception e) {
        super(e);
    }
}
