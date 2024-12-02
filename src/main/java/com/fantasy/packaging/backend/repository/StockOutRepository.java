package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StockOutRepository extends JpaRepository<StockOut, Long> {
  List<StockOut> findByReelNumber(String reelNumber);
  List<StockOut> findAllByOrderByOutTimestampDesc();
}