package com.example.demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MessageProducer {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    private static final String TOPIC = "json-topic";

    public MessageProducer(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Message message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendMessageSync(Message message) throws ExecutionException, InterruptedException {
        kafkaTemplate.send(TOPIC, message).get(); // waits for Kafka to ack
    }
}
