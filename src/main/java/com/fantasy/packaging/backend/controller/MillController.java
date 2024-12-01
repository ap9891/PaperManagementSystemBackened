package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.entity.Mill;
import com.fantasy.packaging.backend.service.MillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mills")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class MillController {
  private final MillService millService;

  @GetMapping
  public ResponseEntity<List<Mill>> getAllMills() {
    return ResponseEntity.ok(millService.getAllMills());
  }

  @GetMapping("/next-id")
  public ResponseEntity<String> getNextMillId() {
    return ResponseEntity.ok(millService.generateNextMillId());
  }

  @PostMapping
  public ResponseEntity<Mill> saveMill(@Valid @RequestBody Mill mill) {
    Mill savedMill = millService.saveMill(mill);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedMill);
  }

  @DeleteMapping("/{millId}")
  public ResponseEntity<Void> deleteMill(@PathVariable String millId) {
    millService.deleteMill(millId);
    return ResponseEntity.noContent().build();
  }
}