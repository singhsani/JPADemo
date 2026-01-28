package com.jpaimplementation.jpaDemo.KafkaProducer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "spring-test-group")
    public void consume(String message, Acknowledgment ack) {

        try {
            System.out.println("Consumed message: " + message);

            // business logic here

            ack.acknowledge(); // commit offset
        } catch (Exception e) {
            System.err.println("Error processing message: " + message);
            throw e; // retry
        }
    }
}
