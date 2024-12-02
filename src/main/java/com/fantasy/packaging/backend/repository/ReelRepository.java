package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.Reel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReelRepository extends JpaRepository<Reel, Long> {
  Optional<Reel> findByReelNumber(String reelNumber);

  @Query("SELECT r FROM Reel r WHERE " +
      "LOWER(r.paperName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(r.reelNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
      "LOWER(r.shade) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
  List<Reel> searchReels(@Param("searchTerm") String searchTerm);
}