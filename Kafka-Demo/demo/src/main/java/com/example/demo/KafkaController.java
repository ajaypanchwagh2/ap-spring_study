package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    private final MessageProducer producer;

    public KafkaController(MessageProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        try {
            producer.sendMessageSync(message);  // call sync method
            return ResponseEntity.ok("✅ Message sent successfully to Kafka topic!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Failed to send message: " + e.getMessage());
        }
    }
}