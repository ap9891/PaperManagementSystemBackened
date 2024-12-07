package com.fantasy.packaging.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
  @Size(min = 3, max = 50, message = "Mill name must be between 3 and 50 characters")
  @Column(name = "mill_name", nullable = false, unique = true)
  private String millName;
}