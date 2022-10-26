package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TransaksiService {
    @Autowired
    private TransaksiRepository transaksiRepository;

    public List<Transaksi> getTransaksiBetween(Date start, Date end) {
        List <Transaksi> currentRekening = transaksiRepository.getTransaksiBetweenDate(start, end);
        return currentRekening;
    }

}
