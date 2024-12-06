package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.PaperPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaperPurchaseRepository extends
    JpaRepository<PaperPurchase, Long>,
    JpaSpecificationExecutor<PaperPurchase> {

  Optional<PaperPurchase> findByReelNumber(String reelNumber);

  // Existing query method can remain
  @org.springframework.data.jpa.repository.Query("SELECT p FROM PaperPurchase p ORDER BY p.reelNumber DESC")
  java.util.List<PaperPurchase> findRecentPurchases();
}