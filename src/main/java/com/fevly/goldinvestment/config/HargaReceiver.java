package com.fevly.goldinvestment.config;

import java.util.concurrent.CountDownLatch;

import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.service.InputHargaStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class HargaReceiver {
    @Autowired
    InputHargaStorage inputHargaStorage;

    private static final Logger LOGGER = LoggerFactory.getLogger(HargaReceiver.class);
    private CountDownLatch latch = new CountDownLatch(1);
    public CountDownLatch getLatch() {
        return latch;
    }
    public Harga sendToDB (Harga harga){
        return inputHargaStorage.addHarga(harga);
    }

    @KafkaListener(topics = "input-harga")
    public void receive(Harga harga) {
        LOGGER.info("received harga='{}'", harga.toString());
        latch.countDown();


    }
}