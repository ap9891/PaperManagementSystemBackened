package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.dto.ForgotPasswordRequest;
import com.fantasy.packaging.backend.dto.ResetPasswordRequest;
import com.fantasy.packaging.backend.model.User;
import com.fantasy.packaging.backend.repository.UserRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class PasswordResetService {
  private final UserRepository userRepository;
  private final JavaMailSender mailSender;
  private final PasswordEncoder passwordEncoder;

  private static final int OTP_VALIDITY_MINUTES = 10;
  private static final int MAX_OTP_ATTEMPTS = 3;

  @Autowired
  public PasswordResetService(
      UserRepository userRepository,
      JavaMailSender mailSender,
      PasswordEncoder passwordEncoder
  ) {
    this.userRepository = userRepository;
    this.mailSender = mailSender;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean sendOTP(ForgotPasswordRequest request) {
    log.info("Attempting to find user with email: {}", request.getEmail());

    Optional<User> userOptional = userRepository.findByEmail(request.getEmail());

    if (userOptional.isEmpty()) {
      log.error("No user found with email: {}", request.getEmail());
      throw new RuntimeException("User not found with email: " + request.getEmail());
    }

    User user = userOptional.get();
    if (user.getOtpAttempts() >= MAX_OTP_ATTEMPTS) {
      throw new RuntimeException("Maximum OTP attempts exceeded. Please try again later.");
    }
    String otp = generateOTP();

    // Save OTP details
    user.setOtp(otp);
    user.setOtpGeneratedAt(LocalDateTime.now());
    user.setOtpAttempts(user.getOtpAttempts() + 1);
    userRepository.save(user);

    // Send OTP via email
    sendOTPEmail(user.getEmail(), otp);

    return true;
  }

  public boolean resetPassword(ResetPasswordRequest request) {
    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("User not found"));

    // Validate OTP
    validateOTP(user, request.getOtp());

    // Reset password
    user.setPassword(passwordEncoder.encode(request.getNewPassword()));

    // Clear OTP fields
    user.setOtp(null);
    user.setOtpGeneratedAt(null);
    user.setOtpAttempts(0);

    userRepository.save(user);

    return true;
  }

  private void validateOTP(User user, String inputOTP) {
    if (user.getOtp() == null || !user.getOtp().equals(inputOTP)) {
      throw new RuntimeException("Invalid OTP");
    }

    LocalDateTime otpGeneratedAt = user.getOtpGeneratedAt();
    if (otpGeneratedAt == null ||
        LocalDateTime.now().isAfter(otpGeneratedAt.plusMinutes(OTP_VALIDITY_MINUTES))) {
      throw new RuntimeException("OTP has expired");
    }
  }

  private String generateOTP() {
    return String.format("%06d", new Random().nextInt(999999));
  }

  private void sendOTPEmail(String to, String otp) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setFrom("noreply@yourcompany.com");
      message.setTo(to);
      message.setSubject("Password Reset OTP");
      message.setText("Your OTP for password reset is: " + otp +
          "\nThis OTP is valid for " + OTP_VALIDITY_MINUTES + " minutes.");

      mailSender.send(message);
    } catch (Exception e) {
      log.error("Error sending OTP email", e);
      throw new RuntimeException("Failed to send OTP email");
    }
  }
}