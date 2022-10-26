package com.fevly.goldinvestment.service;


import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.helper.Buyback;
import com.fevly.goldinvestment.helper.DateUtility;
import com.fevly.goldinvestment.repository.HargaRepository;
import com.fevly.goldinvestment.repository.RekeningRepository;
import com.fevly.goldinvestment.repository.TopUpRepository;
import com.fevly.goldinvestment.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class BuybackStorage {
    @Autowired
    private TopUpRepository topUpRepository;
    @Autowired
    private RekeningRepository rekeningRepository;
    @Autowired
    private TransaksiRepository transaksiRepository;
    @Autowired
    private HargaRepository hargaRepository;

    // will thrown exception during message streaming, but will STILL deliver
    // cannot debug futher, RAM is limited, CPU is old, things are slow and starts burning
    public Buyback buyback (Buyback buyback) {
        Transaksi transaksi = new Transaksi();
        transaksi.setDate(Date.valueOf(new DateUtility().getFormattedDate("yyyy-MM-dd")));
        transaksi.setType("buyback");
        transaksi.setGram(buyback.getGram());
        transaksi.setHarga(buyback.getHarga());

        Rekening rekening = rekeningRepository.getByNorek(buyback.getNorek());
        transaksiRepository.save(transaksi);
        updateSaldo(buyback.getNorek(), buyback.getHarga());
        return buyback;

    }

    public Rekening updateSaldo(String norek, double harga) {
        Rekening rekening = rekeningRepository.getByNorek(norek);
        rekening.setSaldo(rekening.getSaldo() + harga);
        return rekeningRepository.save(rekening);
    }

}
