package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.PaperMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaperMasterRepository extends JpaRepository<PaperMaster, Long> {
  Optional<PaperMaster> findByPartNumber(String partNumber);

  @Query("SELECT pm FROM PaperMaster pm WHERE " +
      "(:searchTerm IS NULL OR LOWER(pm.partName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
      "(:reelSize IS NULL OR pm.reelSize = :reelSize) AND " +
      "(:gsm IS NULL OR pm.gsm = :gsm) AND " +
      "(:bf IS NULL OR pm.bf = :bf)")
  List<PaperMaster> searchPaperMasters(
      @Param("searchTerm") String searchTerm,
      @Param("reelSize") Integer reelSize,
      @Param("gsm") Integer gsm,
      @Param("bf") Integer bf
  );
}