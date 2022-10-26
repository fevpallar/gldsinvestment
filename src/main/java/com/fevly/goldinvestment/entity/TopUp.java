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
    @Id
    @GeneratedValue
    private Long id;
    private double gram;
    private double harga; // total harga pembelian
    private String norek;
}
