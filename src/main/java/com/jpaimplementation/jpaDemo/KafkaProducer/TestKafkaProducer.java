package com.jpaimplementation.jpaDemo.KafkaProducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestKafkaProducer {
    private static final String TOPIC = "test-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TestKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Message sent to Kafka: " + message);
    }
}
