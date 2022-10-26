package com.fevly.goldinvestment.config;

import com.fevly.goldinvestment.entity.Harga;
import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;


public class HargaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("input-harga")
    private String jsonTopic;

    @Autowired
    private KafkaTemplate<String, Harga> kafkaTemplate;

    public void send(Harga harga) {
        LOGGER.info("sending harga='{}'", harga.toString());
        kafkaTemplate.send(jsonTopic, harga);
    }
}