package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.dto.PaperPurchaseDTO;
import com.fantasy.packaging.backend.service.PaperPurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/paper-purchases")
@RequiredArgsConstructor
public class PaperPurchaseController {
  private final PaperPurchaseService service;

  @PostMapping
  public ResponseEntity<PaperPurchaseDTO> createPaperPurchase(
      @Valid @RequestBody PaperPurchaseDTO paperPurchaseDTO) {
    PaperPurchaseDTO savedPurchase = service.createPaperPurchase(paperPurchaseDTO);
    return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PaperPurchaseDTO>> getAllPaperPurchases() {
    List<PaperPurchaseDTO> purchases = service.getAllPaperPurchases();
    return ResponseEntity.ok(purchases);
  }

  @GetMapping("/generate-reel-number")
  public ResponseEntity<String> generateReelNumber() {
    String reelNumber = service.generateReelNumber();
    return ResponseEntity.ok(reelNumber);
  }
}