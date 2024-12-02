package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.dto.ReelOutRequest;
import com.fantasy.packaging.backend.entity.Reel;
import com.fantasy.packaging.backend.entity.ReelHistory;
import com.fantasy.packaging.backend.entity.StockOut;
import com.fantasy.packaging.backend.repository.StockOutRepository;
import com.fantasy.packaging.backend.service.ReelService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reels")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3001")
public class ReelController {
  private final ReelService reelService;
  private final StockOutRepository stockOutRepository;


  @GetMapping
  public ResponseEntity<List<Reel>> getAllReels() {
    return ResponseEntity.ok(reelService.getAllAvailableReels());
  }

  @PostMapping("/stock-out")
  public ResponseEntity<StockOut> stockOutReel(@RequestBody Map<String, Object> payload) {
    String reelNumber = (String) payload.get("reelNumber");
    Number outQuantityNum = (Number) payload.get("outQuantity");
    double outQuantity = outQuantityNum.doubleValue();

    StockOut stockOut = reelService.stockOutReel(reelNumber, outQuantity);
    return ResponseEntity.ok(stockOut);
  }


  @GetMapping("/search")
  public ResponseEntity<List<Reel>> searchReels(
      @RequestParam(name = "searchTerm", required = false) String searchTerm
  ) {
    if (searchTerm == null || searchTerm.isEmpty()) {
      return ResponseEntity.ok(reelService.getAllAvailableReels());
    }
    return ResponseEntity.ok(reelService.searchReels(searchTerm));
  }

  @GetMapping("/history")
  public ResponseEntity<List<StockOut>> getStockOutHistory() {
    // Order by most recent first
    List<StockOut> history = stockOutRepository.findAllByOrderByOutTimestampDesc();
    return ResponseEntity.ok(history);
  }

}