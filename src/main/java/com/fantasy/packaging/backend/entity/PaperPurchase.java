package com.fantasy.packaging.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paper_purchases")
public class PaperPurchase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDate date;

  @Column(unique = true, nullable = false)
  private String reelNumber;

  @Column(nullable = false)
  private String paperName;

  @Column(nullable = false)
  @Min(value = 1, message = "Quantity must be at least 1")
  @Max(value = 2000, message = "Quantity must not exceed 2000")
  private Integer quantity;

  @Column(nullable = false)
  private String millName;

  @Column(nullable = false)
  private String shade;

  @Min(value = 1, message = "Rate must be at least 1")
  @Column(nullable = false)
  @Digits(integer = 10, fraction = 2)
  private BigDecimal ratePerKg;

  @Column(nullable = false)
  @Digits(integer = 10, fraction = 2)
  private BigDecimal price;

  private String remark;
}