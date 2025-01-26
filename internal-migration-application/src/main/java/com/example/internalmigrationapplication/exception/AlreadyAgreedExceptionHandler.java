package com.example.internalmigrationapplication.exception;

import com.example.migrationservice.application.user.AlreadyAgreedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AlreadyAgreedExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AlreadyAgreedException.class)
    public ResponseEntity<?> handleAlreadyAgreed() {
        return ResponseEntity.badRequest().build();
    }
}
