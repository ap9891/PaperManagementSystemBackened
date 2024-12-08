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
  public ResponseEntity<List<PaperPurchaseDTO>> quickSearchInventory(
      @RequestParam String searchTerm
  ) {
    List<PaperPurchaseDTO> inventoryList = inventoryService.searchPaperPurchases(searchTerm);
    return ResponseEntity.ok(inventoryList);
  }

}