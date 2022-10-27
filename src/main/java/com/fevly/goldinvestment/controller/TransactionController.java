package com.fevly.goldinvestment.controller;

import com.fevly.goldinvestment.config.BuybackReceiver;
import com.fevly.goldinvestment.config.HargaReceiver;
import com.fevly.goldinvestment.config.TopUpReceiver;
import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.Rekening;
import com.fevly.goldinvestment.entity.TopUp;
import com.fevly.goldinvestment.entity.Transaksi;
import com.fevly.goldinvestment.handling.ErrorEntity;
import com.fevly.goldinvestment.helper.Buyback;
import com.fevly.goldinvestment.helper.HargaDTO;
import com.fevly.goldinvestment.helper.Mutasi;
import com.fevly.goldinvestment.helper.NoRekening;
import com.fevly.goldinvestment.service.*;
import com.fevly.goldinvestment.ui.UI;
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

    @Autowired
    HargaReceiver hargaReceiver;
    @Autowired
    InputHargaStorage inputHargaStorage;
    @Autowired
    InputHargaService inputHargaService;

    @Autowired
    TopUpReceiver topUpReceiver;
    @Autowired
    TopUpStorage topUpStorage;
    @Autowired
    TopUpService topUpService;

    @Autowired
    BuybackReceiver buybackReceiver;
    @Autowired
    BuybackStorage buybackStorage;
    @Autowired
    BuybackService buybackService;

    @Autowired
    RekeningService rekeningService;
    @Autowired
    MutasiService mutasiService;


    @PostMapping("/api/saldo")
    public ResponseEntity<?> checkSaldo(@RequestBody NoRekening norek) {

        Map<String, Rekening> map;
        Rekening rekening = rekeningService.checkSaldo(norek.getNorek().trim());
        if (rekening == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else {
            UI.setGridSaldo(rekening);
            map = new LinkedHashMap<String, Rekening>();
            map.put("data", rekening);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Map<String, Rekening>>(map, headers, HttpStatus.OK);
    }

    @GetMapping("/api/check-harga")
    public ResponseEntity<?> getAllHarga() {
        Map<String, List<HargaDTO>> map;
        List<HargaDTO> listHarga = inputHargaStorage.getAllHargaTopupBuyback();
        if (listHarga == null)
            return new ResponseEntity<>(null, HttpStatus.OK);
        else {
            map = new LinkedHashMap<>();
            map.put("data", listHarga);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            return new ResponseEntity<Map<String, List<HargaDTO>>>(map, headers, HttpStatus.OK);
        }
    }

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
        if (topUp.getGram() >= (double) (1 / 1000) && topUp.getGram() % (double) (1 / 1000) == 0) {
            topUpService.sendTopUp(topUp);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
            return new ResponseEntity<TopUp>(topUpReceiver.sendToDB(topUp), headers, HttpStatus.OK);
        } else {
            ErrorEntity err = new ErrorEntity();
            err.setError("true");
            err.setReff_id(topUpStorage.getLastRowId().toString());
            err.setMessage("jumlah emas minimal 0.001 dan kelipatan 0.001");
            return new ResponseEntity<ErrorEntity>(err, HttpStatus.OK);
        }

    }

    @PostMapping("/api/buyback")
    public ResponseEntity<?> buyBack(@RequestBody Buyback buyback) {
        buybackService.send(buyback);
        Buyback buybackDomain = new Buyback();
        buybackDomain.setGram(buyback.getGram());
        buybackDomain.setHarga(buyback.getHarga());
        buybackDomain.setNorek(buyback.getNorek());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Buyback>(buybackReceiver.sendToDB(buyback), headers, HttpStatus.OK);
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
