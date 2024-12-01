package com.fantasy.packaging.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "mills")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mill implements Serializable {
  @Id
  @Column(name = "mill_id")
  private String millId;

  @NotBlank(message = "Mill name is required")
  @Column(name = "mill_name", nullable = false)
  private String millName;
}