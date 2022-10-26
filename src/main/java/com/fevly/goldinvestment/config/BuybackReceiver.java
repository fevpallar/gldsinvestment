package com.fevly.goldinvestment.config;


import com.fevly.goldinvestment.helper.Buyback;
import com.fevly.goldinvestment.service.BuybackStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;


@Service
public class BuybackReceiver {
    @Autowired
    BuybackStorage buybackStorage;

    private static final Logger LOGGER = LoggerFactory.getLogger(BuybackReceiver.class);
    private CountDownLatch latch = new CountDownLatch(1);
    public CountDownLatch getLatch() {
        return latch;
    }

    public Buyback sendToDB (Buyback buyback){
        return buybackStorage.buyback(buyback);
    }
    @KafkaListener(topics = "topup")
    public void receive(Buyback buyback) {
        LOGGER.info("received Buyback='{}'", buyback.toString());
        latch.countDown();

    }
}