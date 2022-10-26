package com.fevly.goldinvestment.service;


import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.TopUp;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.helper.DateUtility;
import com.fevly.goldinvestment.repository.HargaRepository;
import com.fevly.goldinvestment.repository.RekeningRepository;
import com.fevly.goldinvestment.repository.TopUpRepository;
import com.fevly.goldinvestment.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TopUpStorage {
    @Autowired
    private TopUpRepository topUpRepository;
    @Autowired
    private RekeningRepository rekeningRepository;
    @Autowired
    private TransaksiRepository transaksiRepository;
    @Autowired
    private HargaRepository hargaRepository;

    public TopUp insertTransaksi(TopUp topUp) {
        Transaksi transaksi = new Transaksi();
        transaksi.setDate(Date.valueOf(new DateUtility().getFormattedDate("yyyy-MM-dd")));
        transaksi.setType("topup");
        transaksi.setGram(topUp.getGram());
        transaksi.setHarga(topUp.getHarga());
      /*  transaksi.setHarga_topup(topUp.getHarga());
        transaksi.setHarga_buyback(hargaRepository.getHargaBuyback());*/


        Rekening rekening = rekeningRepository.getByNorek(topUp.getNorek());
        //transaksi.setSaldo(rekening.getSaldo());
        transaksiRepository.save(transaksi);
        updateSaldo(topUp.getNorek(), topUp.getHarga());
        return topUpRepository.save(topUp);
    }

    public Rekening updateSaldo(String norek, double harga) {
        Rekening rekening = rekeningRepository.getByNorek(norek);
        rekening.setSaldo(rekening.getSaldo() -harga);
        return rekeningRepository.save(rekening);
    }

    public Long getLastRowId() {
        List<TopUp> lastHarga = topUpRepository.getAllTopUp();
        Long lastTopUpId = lastHarga.get(lastHarga.size() - 1).getId();
        return lastTopUpId;
    }

    public List<TopUp> getAllTopUp() {
        return topUpRepository.getAllTopUp();
    }

}
