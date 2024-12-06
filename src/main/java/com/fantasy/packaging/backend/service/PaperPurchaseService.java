package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.dto.PaperPurchaseDTO;
import com.fantasy.packaging.backend.entity.PaperPurchase;
import com.fantasy.packaging.backend.repository.PaperPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaperPurchaseService {
  private final PaperPurchaseRepository repository;

  // Generate Reel Number Logic
  public String generateReelNumber() {
    LocalDate now = LocalDate.now();
    int financialYear = now.getMonth().getValue() > 3 ? now.getYear() : now.getYear() - 1;
    String prefix = String.valueOf((char) (65 + (financialYear % 26)));

    // Find the last reel number for this financial year
    List<PaperPurchase> yearPurchases = repository.findAll().stream()
        .filter(p -> p.getReelNumber().startsWith(prefix))
        .collect(Collectors.toList());

    int number = yearPurchases.size() + 1;
    return prefix + "-" + number;
  }

  @Transactional
  public PaperPurchaseDTO createPaperPurchase(PaperPurchaseDTO dto) {
    // Calculate price if not provided
    if (dto.getPrice() == null && dto.getQuantity() != null && dto.getRatePerKg() != null) {
      dto.setPrice(BigDecimal.valueOf(dto.getQuantity()).multiply(dto.getRatePerKg()));
    }

    // Generate reel number if not provided
    if (dto.getReelNumber() == null || dto.getReelNumber().isEmpty()) {
      dto.setReelNumber(generateReelNumber());
    }

    PaperPurchase purchase = convertToEntity(dto);
    PaperPurchase savedPurchase = repository.save(purchase);
    return convertToDTO(savedPurchase);
  }

  public List<PaperPurchaseDTO> getAllPaperPurchases() {
    return repository.findRecentPurchases().stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
  }

  // Conversion methods
  private PaperPurchase convertToEntity(PaperPurchaseDTO dto) {
    return PaperPurchase.builder()
        .id(dto.getId())
        .date(dto.getDate())
        .reelNumber(dto.getReelNumber())
        .paperName(dto.getPaperName())
        .quantity(dto.getQuantity())
        .millName(dto.getMillName())
        .shade(dto.getShade())
        .ratePerKg(dto.getRatePerKg())
        .price(dto.getPrice())
        .remark(dto.getRemark())
        .build();
  }

  public PaperPurchaseDTO convertToDTO(PaperPurchase entity) {
    return PaperPurchaseDTO.builder()
        .id(entity.getId())
        .date(entity.getDate())
        .reelNumber(entity.getReelNumber())
        .paperName(entity.getPaperName())
        .quantity(entity.getQuantity())
        .millName(entity.getMillName())
        .shade(entity.getShade())
        .ratePerKg(entity.getRatePerKg())
        .price(entity.getPrice())
        .remark(entity.getRemark())
        .build();
  }
}