package com.fantasy.packaging.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperMasterDTO {
  private Long id;
  private String type = "K";

  @NotNull(message = "Reel size is required")
  private Integer reelSize;

  @NotNull(message = "GSM is required")
  private Integer gsm;

  @NotNull(message = "BF is required")
  private Integer bf;

  private String partNumber;
  private String partName;
}