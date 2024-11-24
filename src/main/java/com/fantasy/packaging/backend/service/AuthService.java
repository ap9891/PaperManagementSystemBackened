package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.dto.LoginRequest;
import com.fantasy.packaging.backend.dto.LoginResponse;
import com.fantasy.packaging.backend.model.User;
import com.fantasy.packaging.backend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Service
@Slf4j
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final String jwtSecret = "your-secret-key"; // Move to application.properties in production

  @Autowired
  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public LoginResponse login(LoginRequest request) {
    log.info("Attempting login for email: {}", request.getEmail());

    User user = userRepository.findByEmail(request.getEmail())
        .orElseThrow(() -> {
          log.error("User not found with email: {}", request.getEmail());
          return new BadCredentialsException("Invalid email or password");
        });

    if (!user.isEnabled()) {
      log.error("User account is disabled: {}", request.getEmail());
      throw new BadCredentialsException("Account is disabled");
    }

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      log.error("Invalid password for user: {}", request.getEmail());
      throw new BadCredentialsException("Invalid email or password");
    }

    String token = generateToken(user);
    log.info("Login successful for user: {}", request.getEmail());

    LoginResponse response = new LoginResponse();
    response.setToken(token);
    response.setEmail(user.getEmail());
    return response;
  }

  private String generateToken(User user) {
    return Jwts.builder()
        .setSubject(user.getEmail())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24 hours
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }
}