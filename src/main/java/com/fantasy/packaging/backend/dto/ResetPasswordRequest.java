package com.fantasy.packaging.backend.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ResetPasswordRequest {
  @NotBlank(message = "Email is required")
  private String email;

  @NotBlank(message = "OTP is required")
  private String otp;

  @NotBlank(message = "New password is required")
  @Size(min = 8, message = "Password must be at least 8 characters long")
  private String newPassword;
}