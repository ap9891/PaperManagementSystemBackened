package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.entity.Mill;
import com.fantasy.packaging.backend.repository.MillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MillService {

  @Autowired
  private MillRepository millRepository;

  public List<Mill> getAllMills() {
    return millRepository.findAll();
  }

  public Mill saveMill(Mill mill) {
    // If mill ID is not provided, generate a new one
    if (mill.getMillId() == null || mill.getMillId().isEmpty()) {
      mill.setMillId(getNextMillId());
    }
    return millRepository.save(mill);
  }

  public Optional<Mill> getMillById(String millId) {
    return millRepository.findById(millId);
  }

  public void deleteMill(String millId) {
    millRepository.deleteById(millId);
  }

  public String getNextMillId() {
    return millRepository.findNextMillId();
  }

  public Mill updateMill(Mill mill) {
    // Ensure the mill exists before updating
    if (millRepository.existsById(mill.getMillId())) {
      return millRepository.save(mill);
    }
    throw new RuntimeException("Mill not found with ID: " + mill.getMillId());
  }
}
