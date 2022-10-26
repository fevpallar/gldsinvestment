package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.config.BuybackSender;
import com.fevly.goldinvestment.config.HargaSender;
import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.helper.Buyback;
import com.fevly.goldinvestment.helper.DateUtility;
import com.fevly.goldinvestment.repository.HargaRepository;
import com.fevly.goldinvestment.repository.RekeningRepository;
import com.fevly.goldinvestment.repository.TopUpRepository;
import com.fevly.goldinvestment.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class BuybackService {
    @Autowired
    private BuybackSender sender;
    public void send (Buyback buyback){
        sender.send(buyback);
    }
}
