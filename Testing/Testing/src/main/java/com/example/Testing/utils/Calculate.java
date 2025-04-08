package com.example.Testing.utils;

import org.springframework.stereotype.Component;

@Component
public class Calculate {

    public int addition(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }
}
