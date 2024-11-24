package com.fantasy.packaging.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("/api")
//public class HealthController {
//
//  @GetMapping("/health")
//  public ResponseEntity<String> healthCheck() {
//    return ResponseEntity.ok("Service is healthy");
//  }
//}

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3001") // Add your frontend URL
public class HealthController {

  @GetMapping("/health")
  public ResponseEntity<Map<String, String>> healthCheck() {
    Map<String, String> response = new HashMap<>();
    response.put("status", "Service is healthy");
    return ResponseEntity.ok(response);
  }
}