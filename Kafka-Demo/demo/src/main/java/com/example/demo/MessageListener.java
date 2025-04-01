package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @KafkaListener(topics = "json-topic", groupId = "json-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(Message message) {
        System.out.println("🎧 Received Message -> " + message.getSender() + ": " + message.getContent());
    }
}