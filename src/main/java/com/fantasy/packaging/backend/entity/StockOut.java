package com.fantasy.packaging.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock_outs")
public class StockOut {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "reel_number", nullable = false)
  private String reelNumber;

  @Column(name = "paper_name", nullable = false)
  private String paperName;

  @Column(nullable = false)
  private Double quantityUsed;

  @Column(nullable = false)
  private Double quantityLeft;

  @Column(name = "out_timestamp", nullable = false)
  private LocalDateTime outTimestamp = LocalDateTime.now();

  @Column(name = "mill_name")
  private String millName;

  @Column
  private String shade;

  @Column(name = "rate_per_kg")
  private BigDecimal ratePerKg;
}

