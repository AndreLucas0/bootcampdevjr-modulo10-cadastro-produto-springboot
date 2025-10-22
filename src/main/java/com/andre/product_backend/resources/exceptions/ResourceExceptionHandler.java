package com.andre.product_backend.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.andre.product_backend.service.exceptions.DataBaseException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrors> validationException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        
        ValidationErrors error = new ValidationErrors();

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

        error.setError("Validation error");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimeStamp(Instant.now());

        exception.getBindingResult()
                 .getFieldErrors()
                 .forEach(e -> error.addError(e.getDefaultMessage()));

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> databaseException(DataBaseException exception, HttpServletRequest request) {
        
        StandardError error = new StandardError();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        error.setError("Database exception");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimeStamp(Instant.now());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFoundException(EntityNotFoundException exception, HttpServletRequest request) {
        
        StandardError error = new StandardError();

        HttpStatus status = HttpStatus.NOT_FOUND;

        error.setError("Resource not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(status.value());
        error.setTimeStamp(Instant.now());

        return ResponseEntity.status(status).body(error);
    }
}
