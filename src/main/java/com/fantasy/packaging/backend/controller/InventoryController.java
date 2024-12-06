package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.dto.PaperPurchaseDTO;
import com.fantasy.packaging.backend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping("/search")
  public ResponseEntity<List<PaperPurchaseDTO>> searchInventory(
      @RequestParam(required = false) String paperName,
      @RequestParam(required = false) String shade,
      @RequestParam(required = false) String reelNumber,
      @RequestParam(required = false) String millName,
      @RequestParam(required = false) Integer quantity
  ) {
    List<PaperPurchaseDTO> inventoryList = inventoryService.searchInventory(
        paperName, shade, reelNumber, millName, quantity
    );
    return ResponseEntity.ok(inventoryList);
  }
}