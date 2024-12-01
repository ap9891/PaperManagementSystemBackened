package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.entity.Mill;
import com.fantasy.packaging.backend.service.MillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/mills")
@CrossOrigin(origins = "http://localhost:3001")
public class MillController {

  @Autowired
  private MillService millService;

  @GetMapping
  public ResponseEntity<List<Mill>> getAllMills() {
    List<Mill> mills = millService.getAllMills();
    return ResponseEntity.ok(mills);
  }

  @PostMapping
  public ResponseEntity<Mill> createMill(@Valid @RequestBody Mill mill) {
    Mill savedMill = millService.saveMill(mill);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedMill);
  }

  @GetMapping("/next-id")
  public ResponseEntity<String> getNextMillId() {
    String nextMillId = millService.getNextMillId();
    return ResponseEntity.ok(nextMillId);
  }

  @DeleteMapping("/{millId}")
  public ResponseEntity<Void> deleteMill(@PathVariable String millId) {
    millService.deleteMill(millId);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{millId}")
  public ResponseEntity<Mill> updateMill(
      @PathVariable String millId,
      @Valid @RequestBody Mill millDetails
  ) {
    // Ensure the path variable mill ID matches the body
    millDetails.setMillId(millId);
    Mill updatedMill = millService.updateMill(millDetails);
    return ResponseEntity.ok(updatedMill);
  }
}