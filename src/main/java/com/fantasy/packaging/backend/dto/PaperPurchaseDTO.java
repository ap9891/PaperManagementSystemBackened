package com.fantasy.packaging.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaperPurchaseDTO {
  private Long id;

  @NotNull(message = "Date is required")
  private LocalDate date;

  private String reelNumber;

  @NotBlank(message = "Paper name is required")
  private String paperName;

  @Min(value = 1, message = "Quantity must be at least 1")
  @Max(value = 2000, message = "Quantity must not exceed 2000")
  private Integer quantity;

  @NotBlank(message = "Mill name is required")
  private String millName;

  @NotBlank(message = "Shade is required")
  private String shade;

  @NotNull(message = "Rate per kg is required")
  @Digits(integer = 10, fraction = 2)
  private BigDecimal ratePerKg;

  private BigDecimal price;

  private String remark;
}