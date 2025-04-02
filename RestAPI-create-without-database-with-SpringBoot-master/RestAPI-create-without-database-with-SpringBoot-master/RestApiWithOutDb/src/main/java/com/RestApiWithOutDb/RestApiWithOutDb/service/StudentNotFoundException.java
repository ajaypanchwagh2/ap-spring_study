package com.RestApiWithOutDb.RestApiWithOutDb.service;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}