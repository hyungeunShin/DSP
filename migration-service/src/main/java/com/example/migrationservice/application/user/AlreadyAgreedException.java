package com.example.migrationservice.application.user;

public class AlreadyAgreedException extends RuntimeException {
    public AlreadyAgreedException(String message) {
        super(message);
    }
}
