package com.fevly.goldinvestment.config;

import java.util.HashMap;
import java.util.Map;

import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.TopUp;
import com.fevly.goldinvestment.helper.Buyback;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class UniSenderConfig {

    @Value("localhost:9092")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, Harga> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, TopUp> producerFactoryTopUp() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public ProducerFactory<String, Buyback> producerFactoryBuyback() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, Harga> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, TopUp> kafkaTemplateTopUp() {
        return new KafkaTemplate<>(producerFactoryTopUp());
    }

    @Bean
    public KafkaTemplate<String, Buyback> kafkaTemplateBuyback() {
        return new KafkaTemplate<>(producerFactoryBuyback());
    }

    @Bean
    public HargaSender sender() {
        return new HargaSender();
    }

    @Bean
    public TopUpSender senderTopUp() {
        return new TopUpSender();
    }

    @Bean
    public BuybackSender senderBuyback() {
        return new BuybackSender();
    }

}