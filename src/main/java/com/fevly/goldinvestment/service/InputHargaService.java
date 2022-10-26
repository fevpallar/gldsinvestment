package com.fevly.goldinvestment.service;

import com.fevly.goldinvestment.config.HargaSender;
import com.fevly.goldinvestment.entity.Harga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InputHargaService {
    @Autowired
    private HargaSender sender;
   public void sendHarga (Harga harga){
       sender.send(harga);
   }

}
