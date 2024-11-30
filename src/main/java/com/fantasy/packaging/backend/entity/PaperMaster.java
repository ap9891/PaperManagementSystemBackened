package com.fantasy.packaging.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paper_master")
public class PaperMaster {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "type", length = 1)
  private String type = "K";

  @NotNull(message = "Reel size is required")
  @Column(name = "reel_size")
  private Integer reelSize;

  @NotNull(message = "GSM is required")
  @Column(name = "gsm")
  private Integer gsm;

  @NotNull(message = "BF is required")
  @Column(name = "bf")
  private Integer bf;

  @Column(name = "part_number", unique = true)
  private String partNumber;

  @Column(name = "part_name")
  private String partName;

  // Method to auto-generate part name based on reel size, GSM, and BF
  public void generatePartName() {
    this.partName = String.format("%d/%d/%d", reelSize, gsm, bf);
  }
}