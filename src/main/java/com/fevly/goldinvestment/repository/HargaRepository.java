package com.fevly.goldinvestment.repository;



import com.fevly.goldinvestment.entity.Harga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HargaRepository extends JpaRepository<Harga, Long> {

    @Query(value = "select * from tbl_harga", nativeQuery = true)
    public List<Harga> getAllHarga();

    @Query(value = "select harga_buyback from tbl_harga", nativeQuery = true)
    public Double getHargaBuyback();

    @Query(value = "select harga_topup from tbl_harga", nativeQuery = true)
    public Double getHargaTopUp();

}
