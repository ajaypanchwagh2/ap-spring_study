package com.example.Crud;

// 400 Bad Request
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}
