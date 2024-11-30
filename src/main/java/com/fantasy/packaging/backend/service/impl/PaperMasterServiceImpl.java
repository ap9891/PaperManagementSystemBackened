package com.fantasy.packaging.backend.service.impl;

import com.fantasy.packaging.backend.dto.PaperMasterDTO;
import com.fantasy.packaging.backend.entity.PaperMaster;
import com.fantasy.packaging.backend.repository.PaperMasterRepository;
import com.fantasy.packaging.backend.service.PaperMasterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaperMasterServiceImpl implements PaperMasterService {

  private final PaperMasterRepository paperMasterRepository;

  @Autowired
  public PaperMasterServiceImpl(PaperMasterRepository paperMasterRepository) {
    this.paperMasterRepository = paperMasterRepository;
  }

  @Override
  public PaperMasterDTO createPaperMaster(PaperMasterDTO paperMasterDTO) {
    PaperMaster paperMaster = new PaperMaster();
    BeanUtils.copyProperties(paperMasterDTO, paperMaster);
    paperMaster.generatePartName();
    paperMaster.setPartNumber(generateUniquePartNumber());

    PaperMaster savedPaperMaster = paperMasterRepository.save(paperMaster);

    PaperMasterDTO savedDTO = new PaperMasterDTO();
    BeanUtils.copyProperties(savedPaperMaster, savedDTO);
    return savedDTO;
  }

  @Override
  public PaperMasterDTO updatePaperMaster(Long id, PaperMasterDTO paperMasterDTO) {
    PaperMaster existingPaperMaster = paperMasterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Paper Master not found"));

    BeanUtils.copyProperties(paperMasterDTO, existingPaperMaster, "id", "partNumber");
    existingPaperMaster.generatePartName();

    PaperMaster updatedPaperMaster = paperMasterRepository.save(existingPaperMaster);

    PaperMasterDTO updatedDTO = new PaperMasterDTO();
    BeanUtils.copyProperties(updatedPaperMaster, updatedDTO);
    return updatedDTO;
  }

  @Override
  public void deletePaperMaster(Long id) {
    PaperMaster paperMaster = paperMasterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Paper Master not found"));

    paperMasterRepository.delete(paperMaster);
  }

  @Override
  public List<PaperMasterDTO> getAllPaperMasters() {
    return paperMasterRepository.findAll().stream()
        .map(paperMaster -> {
          PaperMasterDTO dto = new PaperMasterDTO();
          BeanUtils.copyProperties(paperMaster, dto);
          return dto;
        })
        .collect(Collectors.toList());
  }

  @Override
  public PaperMasterDTO getPaperMasterById(Long id) {
    PaperMaster paperMaster = paperMasterRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Paper Master not found"));

    PaperMasterDTO dto = new PaperMasterDTO();
    BeanUtils.copyProperties(paperMaster, dto);
    return dto;
  }

  @Override
  public List<PaperMasterDTO> searchPaperMasters(String searchTerm, Integer reelSize, Integer gsm, Integer bf) {
    return paperMasterRepository.searchPaperMasters(searchTerm, reelSize, gsm, bf).stream()
        .map(paperMaster -> {
          PaperMasterDTO dto = new PaperMasterDTO();
          BeanUtils.copyProperties(paperMaster, dto);
          return dto;
        })
        .collect(Collectors.toList());
  }

  private String generateUniquePartNumber() {
    long count = paperMasterRepository.count() + 1;
    return String.valueOf(count);
  }
}
