package com.fevly.goldinvestment.repository;

import com.fevly.goldinvestment.entity.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TransaksiRepository extends JpaRepository<Transaksi, Long> {
    @Query(value = "select * from tbl_transaksi where date >= DATE(:start) AND date <= DATE(:end)", nativeQuery = true)
    public List<Transaksi> getTransaksiBetweenDate(Date start , Date end);

}
