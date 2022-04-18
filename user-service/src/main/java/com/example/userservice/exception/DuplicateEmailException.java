package com.example.userservice.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("There is already an account created using "+ email + " .");
    }
}
