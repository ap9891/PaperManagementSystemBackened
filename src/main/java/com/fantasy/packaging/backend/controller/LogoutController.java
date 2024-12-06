package com.fantasy.packaging.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*")
public class LogoutController {

  @PostMapping("/logout")
  public ResponseEntity<Map<String, String>> logout() {
    Map<String, String> response = new HashMap<>();
    response.put("message", "Logged out successfully");
    return ResponseEntity.ok(response);
  }
}