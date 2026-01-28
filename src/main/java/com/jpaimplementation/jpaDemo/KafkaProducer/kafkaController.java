package com.jpaimplementation.jpaDemo.KafkaProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class kafkaController {
    @Autowired
    private final TestKafkaProducer producer;

    public kafkaController(TestKafkaProducer producer) {
        this.producer = producer;
    }

//    public KafkaTestController(TestKafkaProducer producer) {
//        this.producer = producer;
//    }

    @GetMapping("/send")
    public String send(@RequestParam String msg) {
        producer.sendMessage(msg);
        return "Sent to Kafka: " + msg;
    }
}
