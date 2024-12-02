package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.ReelHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReelHistoryRepository extends JpaRepository<ReelHistory, Long> {
}