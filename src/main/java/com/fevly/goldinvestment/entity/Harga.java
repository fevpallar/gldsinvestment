package com.fevly.goldinvestment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_harga")
public class Harga {

    @Id
    @GeneratedValue
    private Long id;
    @Column( unique = true)
    private String admin_id;
    private double harga_topup;
    private double harga_buyback;
}
