package com.fantasy.packaging.backend.dto;

import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class ForgotPasswordRequest {
  @Email(message = "Please provide a valid email address")
  @NotBlank(message = "Email is required")
  private String email;
}