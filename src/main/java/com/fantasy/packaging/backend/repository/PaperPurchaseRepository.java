package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.PaperPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaperPurchaseRepository extends JpaRepository<PaperPurchase, Long> {
  Optional<PaperPurchase> findByReelNumber(String reelNumber);

  // Get recent purchases sorted by reel number in descending order
  @Query("SELECT p FROM PaperPurchase p ORDER BY p.reelNumber DESC")
  List<PaperPurchase> findRecentPurchases();
}