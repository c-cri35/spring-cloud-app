package com.example.userservice.exception;

public class NotFoundException extends RuntimeException{
    public <T> NotFoundException(Class<T> cls, Long id) {
        super(cls.getSimpleName() + " with id: " + id + " does not exist!");
    }
}
