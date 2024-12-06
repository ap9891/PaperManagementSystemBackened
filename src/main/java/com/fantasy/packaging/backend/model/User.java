package com.fantasy.packaging.backend.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Email
  @Column(unique = true)
  private String email;

  @NotBlank
  private String password;

  private boolean enabled = true;
  private String resetToken;

  private String otp;

  private LocalDateTime otpGeneratedAt;

  private Integer otpAttempts = 0;
}
