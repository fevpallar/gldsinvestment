package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.config.TopUpSender;
import com.fevly.goldinvestment.entity.TopUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopUpService {

    @Autowired
    private TopUpSender sender;
    public void sendTopUp (TopUp topUp){

        sender.send(topUp);
    }

}
