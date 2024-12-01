package com.fantasy.packaging.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shade_master")
public class ShadeMaster {
  @Id
  @Column(name = "shade_id")
  private String shadeId;

  @NotBlank(message = "Shade name is required")
  @Column(name = "shade_name")
  private String shadeName;
}