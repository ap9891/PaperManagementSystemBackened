package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.dto.LoginRequest;
import com.fantasy.packaging.backend.dto.LoginResponse;
import com.fantasy.packaging.backend.service.AuthService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fantasy.packaging.backend.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class AuthController {
  private final AuthService authService;
  private final JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }
  @PostMapping("/validate-token")
  public ResponseEntity<Map<String, Boolean>> validateToken(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.replace("Bearer ", "");
    boolean isValid = jwtUtil.validateToken(token);

    Map<String, Boolean> response = new HashMap<>();
    response.put("valid", isValid);

    return ResponseEntity.ok(response);
  }
}