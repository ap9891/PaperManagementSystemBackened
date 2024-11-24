package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.model.User;
import com.fantasy.packaging.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class TestController {

  private final UserRepository userRepository;

  @GetMapping("/db-test")
  public ResponseEntity<?> testDatabase() {
    try {
      long userCount = userRepository.count();
      return ResponseEntity.ok("Database connection successful. User count: " + userCount);
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body("Database error: " + e.getMessage());
    }
  }

  @GetMapping("/check-user/{email}")
  public ResponseEntity<?> checkUser(@PathVariable String email) {
    try {
      return userRepository.findByEmail(email)
          .map(user -> ResponseEntity.ok("User exists and enabled: " + user.isEnabled()))
          .orElse(ResponseEntity.ok("User not found"));
    } catch (Exception e) {
      return ResponseEntity.status(500)
          .body("Error checking user: " + e.getMessage());
    }
  }
}