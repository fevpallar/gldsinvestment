package com.fevly.goldinvestment.repository;

import com.fevly.goldinvestment.entity.Harga;
import com.fevly.goldinvestment.entity.TopUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopUpRepository extends JpaRepository<TopUp, Long> {

    @Query(value = "select * from tbl_topup", nativeQuery = true)
    public List<TopUp> getAllTopUp();
}
