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

  public List<PaperPurchaseDTO> searchInventory(
      String paperName,
      String shade,
      String reelNumber,
      String millName,
      Integer quantity) {

    Specification<PaperPurchase> spec = (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (paperName != null && !paperName.isEmpty()) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("paperName")),
            "%" + paperName.toLowerCase() + "%"
        ));
      }

      if (shade != null && !shade.isEmpty()) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("shade")),
            "%" + shade.toLowerCase() + "%"
        ));
      }

      if (reelNumber != null && !reelNumber.isEmpty()) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("reelNumber")),
            "%" + reelNumber.toLowerCase() + "%"
        ));
      }

      if (millName != null && !millName.isEmpty()) {
        predicates.add(criteriaBuilder.like(
            criteriaBuilder.lower(root.get("millName")),
            "%" + millName.toLowerCase() + "%"
        ));
      }

      if (quantity != null) {
        predicates.add(criteriaBuilder.equal(root.get("quantity"), quantity));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };

    return paperPurchaseRepository.findAll(spec)
        .stream()
        .map(purchase -> {
          PaperPurchaseDTO dto = paperPurchaseService.convertToDTO(purchase);
          // Calculate days in godown
          dto.setDays(calculateDaysInGodown(purchase.getDate()));
          return dto;
        })
        .collect(Collectors.toList());
  }

  private int calculateDaysInGodown(LocalDate inDate) {
    return (int) java.time.temporal.ChronoUnit.DAYS.between(inDate, LocalDate.now());
  }
}