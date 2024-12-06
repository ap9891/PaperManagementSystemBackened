package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.dto.ForgotPasswordRequest;
import com.fantasy.packaging.backend.dto.ResetPasswordRequest;
import com.fantasy.packaging.backend.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class PasswordResetController {
  private final PasswordResetService passwordResetService;

  @PostMapping("/forgot-password")
  public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
    passwordResetService.sendOTP(request);
    return ResponseEntity.ok("OTP sent successfully");
  }

  @PostMapping("/reset-password")
  public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
    passwordResetService.resetPassword(request);
    return ResponseEntity.ok("Password reset successfully");
  }
}