package com.fantasy.packaging.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReelDTO {
  private Long id;
  private String reelNumber;
  private String paperName;
  private Double quantity;
  private String millName;
  private String shade;
  private Double rate;
  private Integer days;
  private Boolean isPartiallyUsed;
  private LocalDateTime createdAt;

  // Optional: Add a method to convert from PaperPurchase to Reel
  public static ReelDTO fromPaperPurchase(PaperPurchaseDTO paperPurchase) {
    return ReelDTO.builder()
        .reelNumber(paperPurchase.getReelNumber())
        .paperName(paperPurchase.getPaperName())
        .quantity(paperPurchase.getQuantity().doubleValue())
        .millName(paperPurchase.getMillName())
        .shade(paperPurchase.getShade())
        .rate(paperPurchase.getRatePerKg().doubleValue())
        .days(0)  // You might want to calculate or set this differently
        .isPartiallyUsed(false)
        .build();
  }
}