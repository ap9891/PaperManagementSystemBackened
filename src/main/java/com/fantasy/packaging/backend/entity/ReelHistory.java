package com.fantasy.packaging.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reel_history")
public class ReelHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "reel_number", nullable = false)
  private String reelNumber;

  @Column(name = "paper_name", nullable = false)
  private String paperName;

  @Column(name = "quantity_used", nullable = false)
  private Double quantityUsed;

  @Column(name = "quantity_left", nullable = false)
  private Double quantityLeft;

  @Column(name = "mill_name")
  private String millName;

  @Column
  private String shade;

  @Column(name = "rate_per_kg")
  private Double ratePerKg;

  @Column(name = "out_timestamp")
  private LocalDateTime outTimestamp = LocalDateTime.now();
}