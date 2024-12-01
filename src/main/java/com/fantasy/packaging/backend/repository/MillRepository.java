package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.Mill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MillRepository extends JpaRepository<Mill, String> {

  @Query(value = "SELECT CONCAT('MILL', LPAD(COALESCE(MAX(CAST(SUBSTRING(mill_id, 5) AS SIGNED)), 0) + 1, 4, '0')) FROM mills", nativeQuery = true)
  String findNextMillId();
}