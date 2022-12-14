package com.fevly.goldinvestment.service;


import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.helper.HargaDTO;
import com.fevly.goldinvestment.repository.HargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InputHargaStorage {
    @Autowired
    private HargaRepository hargaRepository;

    public Harga addHarga(Harga harga) {
        return hargaRepository.save(harga);
    }

    public Long getLastRowId() {
        List<Harga> lastHarga;

        if (hargaRepository.getAllHarga().size() > 0) {
            lastHarga = hargaRepository.getAllHarga();
            Long lastHargaId = lastHarga.get(lastHarga.size() - 1).getId();

            return lastHargaId;
        }
        return 0L;
    }

    public List<Harga> getAllHarga() {
        return hargaRepository.getAllHarga();
    }

    public List<HargaDTO> getAllHargaTopupBuyback() {
        List<HargaDTO> listHarga = new ArrayList<>();
        for (Harga hrg : hargaRepository.getAllHarga()) {
            HargaDTO hargaDTO = new HargaDTO();
            hargaDTO.setHarga_topup(hrg.getHarga_topup());
            hargaDTO.setHarga_buyback(hrg.getHarga_buyback());
            listHarga.add(hargaDTO);
        }

       return listHarga;
    }

}
