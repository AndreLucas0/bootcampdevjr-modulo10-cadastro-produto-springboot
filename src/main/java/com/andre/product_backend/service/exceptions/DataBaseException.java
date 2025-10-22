package com.andre.product_backend.service.exceptions;

public class DataBaseException extends RuntimeException {
    
    public DataBaseException(String message) {
        super(message);
    }
}
