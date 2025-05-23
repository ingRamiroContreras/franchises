package com.nequi.franchise.app.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<String> handleEntityNotFound(NoResultException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Entity not found: " + ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("Data integrity violation: " + ex.getMessage());
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<String> handlePersistenceException(PersistenceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Persistence error: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Internal server error: " + ex.getMessage());
    }
}
