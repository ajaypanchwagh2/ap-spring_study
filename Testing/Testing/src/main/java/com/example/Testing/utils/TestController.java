package com.example.Testing.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final Calculate calculate;

    public TestController(Calculate calculate) {
        this.calculate = calculate;
    }

    @GetMapping("/add")
    public int testAdd() {
        return calculate.addition(5, 3);
    }

    @GetMapping("/sub")
    public int testSub() {
        return calculate.sub(10, 4);
    }
}

