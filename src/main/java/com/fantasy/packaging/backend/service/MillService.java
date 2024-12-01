package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.entity.Mill;
import com.fantasy.packaging.backend.repository.MillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MillService {
  private final MillRepository millRepository;

  @Transactional(readOnly = true)
  public List<Mill> getAllMills() {
    return millRepository.findAll();
  }

  @Transactional
  public Mill saveMill(Mill mill) {
    // If no mill ID is provided, generate a new one
    if (mill.getMillId() == null || mill.getMillId().isEmpty()) {
      mill.setMillId(generateNextMillId());
    }
    return millRepository.save(mill);
  }

  @Transactional(readOnly = true)
  public String generateNextMillId() {
    return millRepository.generateNextMillId();
  }

  @Transactional
  public void deleteMill(String millId) {
    millRepository.deleteById(millId);
  }
}