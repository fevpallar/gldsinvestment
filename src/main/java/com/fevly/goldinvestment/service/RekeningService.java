package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.repository.HargaRepository;
import com.fevly.goldinvestment.repository.RekeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RekeningService {
    @Autowired
    private RekeningRepository rekeningRepository;

    public Rekening updateRekening(Rekening rekening) {
        Rekening currentRekening = rekeningRepository.getByNorek(rekening.getNorek());
        return rekeningRepository.save(currentRekening);
    }

    public Rekening checkSaldo (String norek){
        Rekening currentRekening = rekeningRepository.getByNorek(norek);
        return currentRekening;
    }

}
