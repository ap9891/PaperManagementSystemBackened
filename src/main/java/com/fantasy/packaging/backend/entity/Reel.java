package com.fantasy.packaging.backend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reels")
public class Reel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "reel_number", unique = true, nullable = false)
  @NotBlank(message = "Reel number is required")
  private String reelNumber;

  @Column(name = "paper_name", nullable = false)
  @NotBlank(message = "Paper name is required")
  private String paperName;

  @Column(nullable = false)
  @Positive(message = "Quantity must be positive")
  private Double quantity;

  @Column(name = "mill_name")
  private String millName;

  @Column
  private String shade;

  @Column
  private Double rate;

  @Column
  private Integer days;

  @Column(name = "is_partially_used")
  private Boolean isPartiallyUsed = false;

  @Column(name = "created_at")
  private LocalDateTime createdAt = LocalDateTime.now();
}