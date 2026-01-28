package com.jpaimplementation.jpaDemo.KafkaProducer;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.stereotype.Service;

@Configuration
@EnableKafkaStreams
public class StreamConfig {



    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {

        KStream<String, String> stream = builder.stream("test-topic");

        stream
                .filter((k, v) -> v.contains("ORDER"))
                .mapValues(v -> v.toUpperCase())
                .to("output-topic");

        return stream;
    }
}
