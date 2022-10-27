package com.fevly.goldinvestment.ui;

import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.Rekening;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Service;


public class UI {

    public static Grid<Rekening> rekeningGrid = new Grid<Rekening>(Rekening.class);
    public static Grid<Harga> hargaGrid = new Grid<Harga>(Harga.class);



    public static Grid<Rekening> setGridSaldo(Rekening rekening) {

        rekeningGrid.setItems(rekening);

        return rekeningGrid;
    }

    public static Grid<Harga> setGridHarga(Harga harga) {
        hargaGrid.setItems(harga);
        return hargaGrid;
    }



}
