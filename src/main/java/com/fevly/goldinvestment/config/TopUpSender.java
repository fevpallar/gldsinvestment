package com.fevly.goldinvestment.config;

import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.TopUp;
import org.apache.kafka.clients.producer.internals.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
public class TopUpSender {
    @Value("topup")
    private String jsonTopic;
    @Autowired
    private KafkaTemplate<String, TopUp> kafkaTemplate;
    public void send(TopUp topUp) {
        kafkaTemplate.send(jsonTopic, topUp);
    }
}