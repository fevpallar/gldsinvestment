package com.fevly.goldinvestment.helper;



import lombok.Data;

import java.io.Serializable;

@Data
public class Buyback implements Serializable { // gak mau serialize grant via spring.json.trusted.packages
    private Double gram;
    private Double harga;
    private String norek;
}
