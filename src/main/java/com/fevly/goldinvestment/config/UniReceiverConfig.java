package com.fevly.goldinvestment.config;

import java.util.HashMap;
import java.util.Map;

import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.helper.Buyback;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@EnableKafka
public class UniReceiverConfig {

    @Value("localhost:9092")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.fevly.goldinvestment.helper");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");

        return props;
    }

    @Bean
    public ConsumerFactory<String, Harga> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(Harga.class));
    }
    @Bean
    public ConsumerFactory<String, Buyback> consumerFactoryBuyback() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
                new JsonDeserializer<>(Buyback.class));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Harga> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Harga> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Buyback> kafkaListenerContainerFactoryBuyback() {
        ConcurrentKafkaListenerContainerFactory<String, Buyback> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactoryBuyback());

        return factory;
    }
    @Bean
    public HargaReceiver receiver() {
        return new HargaReceiver();
    }

    @Bean
    public BuybackReceiver receiverBuyback() {
        return new BuybackReceiver();
    }
}