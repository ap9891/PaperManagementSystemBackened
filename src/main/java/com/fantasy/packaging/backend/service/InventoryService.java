package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.dto.PaperPurchaseDTO;
import com.fantasy.packaging.backend.entity.PaperPurchase;
import com.fantasy.packaging.backend.repository.PaperPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {
  private final PaperPurchaseRepository paperPurchaseRepository;
  private final PaperPurchaseService paperPurchaseService;

  public List<PaperPurchaseDTO> searchPaperPurchases(String searchTerm) {
    // If search term is empty, return recent or all purchases
    if (searchTerm == null || searchTerm.trim().isEmpty()) {
      return paperPurchaseRepository.findRecentPurchases()
          .stream()
          .map(purchase -> {
            PaperPurchaseDTO dto = paperPurchaseService.convertToDTO(purchase);
            dto.setDays(calculateDaysInGodown(purchase.getDate()));
            return dto;
          })
          .collect(Collectors.toList());
    }

    // Existing search logic
    return paperPurchaseRepository.searchPaperPurchases(searchTerm)
        .stream()
        .map(purchase -> {
          PaperPurchaseDTO dto = paperPurchaseService.convertToDTO(purchase);
          dto.setDays(calculateDaysInGodown(purchase.getDate()));
          return dto;
        })
        .collect(Collectors.toList());
  }
  private int calculateDaysInGodown(LocalDate inDate) {
    return (int) java.time.temporal.ChronoUnit.DAYS.between(inDate, LocalDate.now());
  }
}