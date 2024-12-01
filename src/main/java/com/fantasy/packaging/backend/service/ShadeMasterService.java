package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.entity.ShadeMaster;
import com.fantasy.packaging.backend.repository.ShadeMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShadeMasterService {
  @Autowired
  private ShadeMasterRepository shadeMasterRepository;

  public List<ShadeMaster> getAllShades() {
    return shadeMasterRepository.findAll();
  }

  public ShadeMaster createShade(ShadeMaster shade) {
    return shadeMasterRepository.save(shade);
  }

  public ShadeMaster updateShade(String shadeId, ShadeMaster shadeDetails) {
    ShadeMaster existingShade = shadeMasterRepository.findById(shadeId)
        .orElseThrow(() -> new RuntimeException("Shade not found with id: " + shadeId));

    existingShade.setShadeName(shadeDetails.getShadeName());
    return shadeMasterRepository.save(existingShade);
  }

  public void deleteShade(String shadeId) {
    ShadeMaster shade = shadeMasterRepository.findById(shadeId)
        .orElseThrow(() -> new RuntimeException("Shade not found with id: " + shadeId));

    shadeMasterRepository.delete(shade);
  }
}