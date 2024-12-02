package com.fantasy.packaging.backend.service;

import com.fantasy.packaging.backend.dto.ReelOutRequest;
import com.fantasy.packaging.backend.entity.PaperPurchase;
import com.fantasy.packaging.backend.entity.Reel;
import com.fantasy.packaging.backend.entity.ReelHistory;
import com.fantasy.packaging.backend.entity.StockOut;
import com.fantasy.packaging.backend.repository.PaperPurchaseRepository;
import com.fantasy.packaging.backend.repository.ReelHistoryRepository;
import com.fantasy.packaging.backend.repository.ReelRepository;
import com.fantasy.packaging.backend.repository.StockOutRepository;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReelService {
  private final PaperPurchaseRepository paperPurchaseRepository;
  private final ReelRepository reelRepository;
  private final ReelHistoryRepository reelHistoryRepository;
  private final StockOutRepository stockOutRepository;
  private static final Logger log = LoggerFactory.getLogger(ReelService.class);

  @Transactional(readOnly = true)
  public List<Reel> getAllAvailableReels() {
    // Fetch all paper purchases
    List<PaperPurchase> purchases = paperPurchaseRepository.findAll();

    // Transform purchases into reels with remaining quantity
    return purchases.stream().map(purchase -> {
          // Calculate total stock out for this reel
          List<StockOut> stockOuts = stockOutRepository.findByReelNumber(purchase.getReelNumber());
          double totalStockOut = stockOuts.stream()
              .mapToDouble(StockOut::getQuantityUsed)
              .sum();

          // Calculate remaining quantity
          double remainingQuantity = purchase.getQuantity() - totalStockOut;

          // Create reel object
          Reel reel = new Reel();
          reel.setReelNumber(purchase.getReelNumber());
          reel.setPaperName(purchase.getPaperName());
          reel.setQuantity(remainingQuantity);
          reel.setMillName(purchase.getMillName());
          reel.setShade(purchase.getShade());
          reel.setRate(purchase.getRatePerKg().doubleValue());
          reel.setIsPartiallyUsed(totalStockOut > 0);

          // Calculate days since purchase
          reel.setDays(calculateDaysSincePurchase(purchase));

          return reel;
        })
        .filter(reel -> reel.getQuantity() > 0)
        .collect(Collectors.toList());
  }
  public List<Reel> searchReels(String searchTerm) {
    return reelRepository.searchReels(searchTerm);
  }

  public List<ReelHistory> getAllReelHistory() {
    return reelHistoryRepository.findAll();
  }

  @Transactional
  public StockOut stockOutReel(String reelNumber, double outQuantity) {
    // Logging
    log.info("Attempting to stock out reel: {} with quantity: {}", reelNumber, outQuantity);

    // Find the purchase
    PaperPurchase purchase = paperPurchaseRepository.findByReelNumber(reelNumber)
        .orElseThrow(() -> {
          log.error("Reel not found: {}", reelNumber);
          return new RuntimeException("Reel not found");
        });

    // Check if sufficient quantity is available
    List<StockOut> existingStockOuts = stockOutRepository.findByReelNumber(reelNumber);
    double totalStockOut = existingStockOuts.stream()
        .mapToDouble(StockOut::getQuantityUsed)
        .sum();

    if (totalStockOut + outQuantity > purchase.getQuantity()) {
      log.error("Insufficient reel quantity. Total stock out: {}, Requested: {}, Total Quantity: {}",
          totalStockOut, outQuantity, purchase.getQuantity());
      throw new RuntimeException("Insufficient reel quantity");
    }

    // Create stock out entry
    StockOut stockOut = new StockOut();
    stockOut.setReelNumber(reelNumber);
    stockOut.setPaperName(purchase.getPaperName());
    stockOut.setQuantityUsed(outQuantity);
    stockOut.setMillName(purchase.getMillName());
    stockOut.setShade(purchase.getShade());
    stockOut.setRatePerKg(purchase.getRatePerKg());

    // Log before save
    log.info("Saving stock out: {}", stockOut);

    return stockOutRepository.save(stockOut);
  }

  private int calculateDaysSincePurchase(PaperPurchase purchase) {
    return (int) java.time.temporal.ChronoUnit.DAYS.between(purchase.getDate(), LocalDateTime.now().toLocalDate());
  }
}