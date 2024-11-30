//package com.fantasy.packaging.backend.model;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "paper_masters")
//public class PaperMasterModel {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//
//  @Column(name = "part_number", unique = true)
//  private Long partNumber;
//
//  @Column(name = "part_name")
//  private String partName;
//
//  @Column(name = "type")
//  private String type = "K"; // Default value
//
//  @Column(name = "reel_size")
//  private Integer reelSize;
//
//  @Column(name = "gsm")
//  private Integer gsm;
//
//  @Column(name = "bf")
//  private Integer bf;
//
//  // Getters and Setters
//  public Long getId() { return id; }
//  public void setId(Long id) { this.id = id; }
//
//  public Long getPartNumber() { return partNumber; }
//  public void setPartNumber(Long partNumber) { this.partNumber = partNumber; }
//
//  public String getPartName() { return partName; }
//  public void setPartName(String partName) { this.partName = partName; }
//
//  public String getType() { return type; }
//  public void setType(String type) { this.type = type; }
//
//  public Integer getReelSize() { return reelSize; }
//  public void setReelSize(Integer reelSize) { this.reelSize = reelSize; }
//
//  public Integer getGsm() { return gsm; }
//  public void setGsm(Integer gsm) { this.gsm = gsm; }
//
//  public Integer getBf() { return bf; }
//  public void setBf(Integer bf) { this.bf = bf; }
//}
