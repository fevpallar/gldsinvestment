package com.fevly.goldinvestment.controller;

import com.fevly.goldinvestment.config.HargaReceiver;
import com.fevly.goldinvestment.config.TopUpReceiver;
import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.TopUp;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.handling.ErrorEntity;
import com.fevly.goldinvestment.helper.Buyback;
import com.fevly.goldinvestment.helper.Mutasi;
import com.fevly.goldinvestment.helper.NoRekening;
import com.fevly.goldinvestment.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RestController
@RequestMapping("/submit")
public class TransactionController {

    @Autowired HargaReceiver hargaReceiver;
    @Autowired
    InputHargaStorage inputHargaStorage;
    @Autowired
    InputHargaService inputHargaService;

@Autowired
TopUpReceiver topReceiver;
    @Autowired
    TopUpStorage topUpStorage;
    @Autowired
    TopUpService topUpService;

    @Autowired
    RekeningService rekeningService;
    @Autowired
    BuybackService buybackService;
    @Autowired
    MutasiService mutasiService;

    @PostMapping("/api/input-harga")
    public ResponseEntity<?> addHarga(@RequestBody Harga harga) {
        if (harga.getHarga_topup() == 0 || harga.getHarga_buyback() == 0) {
            ErrorEntity err = new ErrorEntity();
            err.setError("true");
            err.setReff_id(topUpStorage.getLastRowId().toString());
            err.setMessage("Nominal harga tidak boleh kosong!");
            return new ResponseEntity<ErrorEntity>(err, HttpStatus.OK);
        }
        inputHargaService.sendHarga(harga);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Harga>(hargaReceiver.sendToDB(harga), headers, HttpStatus.OK);
    }

    @PostMapping("/api/topup")
    public ResponseEntity<?> topUp(@RequestBody TopUp topUp) {
        topUpService.sendTopUp(topUp);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
      return new ResponseEntity<TopUp>(topReceiver.sendToDB(topUp), headers, HttpStatus.OK);
   // return null;

    }

    @PostMapping("/api/buyback")
    public ResponseEntity<?> buyBack(@RequestBody Buyback buyback) {
        Buyback buybackDomain = new Buyback();
        buybackDomain.setGram(buyback.getGram());
        buybackDomain.setHarga(buyback.getHarga());
        buybackDomain.setNorek(buyback.getNorek());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Buyback>(buybackService.buyback(buyback), headers, HttpStatus.OK);
    }

    @PostMapping("/api/saldo")
    public ResponseEntity<?> checkSaldo(@RequestBody NoRekening norek) {

     Rekening rekening = rekeningService.checkSaldo(norek.getNorek());
        if (rekening == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Double>(rekening.getSaldo(), headers, HttpStatus.OK);
    }

    @PostMapping("/api/mutasi")
    public ResponseEntity<?> mutasi(@RequestBody Mutasi mutasi) {
        List<Transaksi> listMutasi = mutasiService.mutasi(mutasi);
        if (listMutasi == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<List<Transaksi>>(listMutasi, headers, HttpStatus.OK);
    }

}
