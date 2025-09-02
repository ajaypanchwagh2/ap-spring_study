package com.example.Crud;

// 404 Not Found
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}

