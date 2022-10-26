package com.fevly.goldinvestment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_topup")
public class TopUp {
// proses topup =Customer dapat melakukan topup,
// yaitu membeli emas di harga saat ini.
    // top up =setoran tunai untuk membeli emas batangan

/*    Cara menabung emas (top up) sangat mudah, bisa melalui outlet Pegadaian,
    ATM, Aplikasi Pegadaian Digital, ataupun Agen Pegadaian. Untuk transaksi
    top up tersebut mulai dari 0,01 gram dan maksimal 100 gram per hari per CIF.*/



    @Id
    @GeneratedValue
    private Long id;
    private double gram;
    private double harga; // total harga pembelian
    private String norek;




}
