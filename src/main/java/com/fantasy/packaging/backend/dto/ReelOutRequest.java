package com.fantasy.packaging.backend.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class ReelOutRequest {
  @NotBlank(message = "Reel number is required")
  private String reelNumber;

  @Positive(message = "Out quantity must be positive")
  private Double outQuantity;
}