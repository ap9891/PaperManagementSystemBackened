package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.PaperPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaperPurchaseRepository extends JpaRepository<PaperPurchase, Long>, JpaSpecificationExecutor<PaperPurchase> {
  Optional<PaperPurchase> findByReelNumber(String reelNumber);

  @Query("SELECT p FROM PaperPurchase p ORDER BY p.reelNumber DESC")
  List<PaperPurchase> findRecentPurchases();

  // New method to search paper purchases similar to the reel search
  @Query("SELECT p FROM PaperPurchase p WHERE " +
      "LOWER(p.paperName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(p.reelNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(p.millName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(p.shade) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
  List<PaperPurchase> searchPaperPurchases(@Param("searchTerm") String searchTerm);
}