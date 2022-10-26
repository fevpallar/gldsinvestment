package com.fevly.goldinvestment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_rekening")
public class Rekening {
    @Id
    @GeneratedValue
    private Long id;
    @Column( unique = true)
    private String norek;
    private  double saldo;
}
