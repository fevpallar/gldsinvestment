package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.helper.Mutasi;
import com.fevly.goldinvestment.repository.HargaRepository;
import com.fevly.goldinvestment.repository.RekeningRepository;
import com.fevly.goldinvestment.repository.TopUpRepository;
import com.fevly.goldinvestment.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutasiService {

    @Autowired
    private TopUpRepository topUpRepository;
    @Autowired
    private RekeningRepository rekeningRepository;
    @Autowired
    private TransaksiRepository transaksiRepository;
    @Autowired
    private HargaRepository hargaRepository;

    public List<Transaksi> mutasi(Mutasi mutasi) {
        List<Transaksi> listTransaksi = transaksiRepository.getTransaksiBetweenDate
                (mutasi.getStart_date(), mutasi.getEnd_date());

        return listTransaksi;

    }

    public Rekening updateSaldo(String norek, double harga) {
        Rekening rekening = rekeningRepository.getByNorek(norek);
        rekening.setSaldo(rekening.getSaldo() + harga);
        return rekeningRepository.save(rekening);
    }
}
