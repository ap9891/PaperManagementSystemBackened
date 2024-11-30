package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.dto.PaperMasterDTO;

import java.util.List;

public interface PaperMasterService {
  PaperMasterDTO createPaperMaster(PaperMasterDTO paperMasterDTO);
  PaperMasterDTO updatePaperMaster(Long id, PaperMasterDTO paperMasterDTO);
  void deletePaperMaster(Long id);
  List<PaperMasterDTO> getAllPaperMasters();
  PaperMasterDTO getPaperMasterById(Long id);
  List<PaperMasterDTO> searchPaperMasters(String searchTerm, Integer reelSize, Integer gsm, Integer bf);
}