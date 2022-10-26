package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.config.HargaSender;
import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.repository.HargaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InputHargaService {
    @Autowired
    private HargaSender sender;
   public void sendHarga (Harga harga){
       sender.send(harga);
   }

}
