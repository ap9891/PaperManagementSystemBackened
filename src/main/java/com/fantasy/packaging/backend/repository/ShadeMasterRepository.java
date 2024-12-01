package com.fantasy.packaging.backend.repository;

import com.fantasy.packaging.backend.entity.ShadeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShadeMasterRepository extends JpaRepository<ShadeMaster, String> {
}