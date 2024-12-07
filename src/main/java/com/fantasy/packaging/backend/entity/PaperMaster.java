package com.fantasy.packaging.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paper_master"
//    uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"reel_size", "gsm", "bf", "type"},
//            name = "unique_paper_master_constraint")
//    }
)
public class PaperMaster {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type", length = 1)
  @Pattern(regexp = "^[KP]$", message = "Type must be either 'K' or 'P'")
  private String type = "K";

  @NotNull(message = "Reel size is required")
  @Min(value = 1, message = "Reel size must be a positive number")
  @Column(name = "reel_size", nullable = false)
  private Integer reelSize;

  @NotNull(message = "GSM is required")
  @Min(value = 1, message = "GSM must be a positive number")
  @Column(name = "gsm", nullable = false)
  private Integer gsm;

  @NotNull(message = "BF is required")
  @Min(value = 1, message = "BF must be a positive number")
  @Column(name = "bf", nullable = false)
  private Integer bf;

  @Column(name = "part_number", unique = true, length = 50)
  @Size(max = 50, message = "Part number must be less than 50 characters")
  private String partNumber;

  @Column(name = "part_name", length = 100)
  @Size(max = 100, message = "Part name must be less than 100 characters")
  private String partName;

  // Improved method to generate part name
  public void generatePartName() {
    // Validate inputs before generating part name
    if (reelSize == null || gsm == null || bf == null) {
      throw new IllegalStateException("Reel size, GSM, and BF must not be null");
    }
    this.partName = String.format("%d/%d/%d", reelSize, gsm, bf);

  }

  // Method to validate entity before persistence
  public void validateEntity() {
    if (reelSize == null || reelSize < 1) {
      throw new IllegalArgumentException("Reel size must be a positive number");
    }
    if (gsm == null || gsm < 1) {
      throw new IllegalArgumentException("GSM must be a positive number");
    }
    if (bf == null || bf < 1) {
      throw new IllegalArgumentException("BF must be a positive number");
    }
    if (type == null || (!type.equals("K") && !type.equals("P"))) {
      throw new IllegalArgumentException("Type must be either 'K' or 'P'");
    }
  }
}