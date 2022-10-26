package com.fevly.goldinvestment.config;

import com.fevly.goldinvestment.entity.TopUp;
import com.fevly.goldinvestment.entity.TopUp;
import com.fevly.goldinvestment.service.TopUpStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
@Service
public class TopUpReceiver {
    @Autowired
    TopUpStorage topUpStorage;

    private static final Logger LOGGER = LoggerFactory.getLogger(TopUpReceiver.class);
    private CountDownLatch latch = new CountDownLatch(1);
    public CountDownLatch getLatch() {
        return latch;
    }

    public TopUp sendToDB (TopUp TopUp){
        return topUpStorage.insertTransaksi(TopUp);
    }
    @KafkaListener(topics = "topup")
    public void receive(TopUp topUp) {
        LOGGER.info("received topup='{}'", topUp.toString());
        latch.countDown();


    }
}