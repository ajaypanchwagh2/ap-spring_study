package com.example.backend;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("Fallback Response: Backend service unavailable");
    }
}
