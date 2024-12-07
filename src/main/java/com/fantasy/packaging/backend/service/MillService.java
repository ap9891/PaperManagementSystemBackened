package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.entity.Mill;
import com.fantasy.packaging.backend.exception.MillAlreadyExistsException;
import com.fantasy.packaging.backend.exception.MillNotFoundException;
import com.fantasy.packaging.backend.repository.MillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

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
    if (mill.getMillId() == null || mill.getMillId().isEmpty()) {
      mill.setMillId(generateNextMillId());
    }
    // Validate mill name uniqueness
    Optional<Mill> existingMill = millRepository.findByMillName(mill.getMillName());
    if (existingMill.isPresent() && !existingMill.get().getMillId().equals(mill.getMillId())) {
      throw new MillAlreadyExistsException("A mill with this name already exists");
    }

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
    if (!millRepository.existsById(millId)) {
      throw new MillNotFoundException("Mill not found with ID: " + millId);
    }
    millRepository.deleteById(millId);
  }
}