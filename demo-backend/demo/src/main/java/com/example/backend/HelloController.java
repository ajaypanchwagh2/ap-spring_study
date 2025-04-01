package com.example.backend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backend")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from Backend Service!";
    }
}
