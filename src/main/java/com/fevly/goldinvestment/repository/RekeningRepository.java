package com.fevly.goldinvestment.repository;



import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RekeningRepository extends JpaRepository<Rekening, Long> {

    @Query(value = "select * from tbl_rekening", nativeQuery = true)
    public List<Rekening> getAllRekening();
    @Query(value = "select * from tbl_rekening where norek=:norek", nativeQuery = true)
    public Rekening getByNorek( String norek);

}