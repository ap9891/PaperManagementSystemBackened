package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.dto.PaperMasterDTO;
import com.fantasy.packaging.backend.service.PaperMasterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/paper-master")
@Tag(name = "Paper Master", description = "Paper Master Management APIs")
@CrossOrigin(origins = "http://localhost:3001")
public class PaperMasterController {

  private final PaperMasterService paperMasterService;

  @Autowired
  public PaperMasterController(PaperMasterService paperMasterService) {
    this.paperMasterService = paperMasterService;
  }

  @PostMapping
  @Operation(summary = "Create a new Paper Master")
  public ResponseEntity<PaperMasterDTO> createPaperMaster(@Valid @RequestBody PaperMasterDTO paperMasterDTO) {
    PaperMasterDTO createdPaperMaster = paperMasterService.createPaperMaster(paperMasterDTO);
    return new ResponseEntity<>(createdPaperMaster, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update an existing Paper Master")
  public ResponseEntity<PaperMasterDTO> updatePaperMaster(
      @PathVariable Long id,
      @Valid @RequestBody PaperMasterDTO paperMasterDTO) {
    PaperMasterDTO updatedPaperMaster = paperMasterService.updatePaperMaster(id, paperMasterDTO);
    return ResponseEntity.ok(updatedPaperMaster);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete a Paper Master")
  public ResponseEntity<Void> deletePaperMaster(@PathVariable Long id) {
    paperMasterService.deletePaperMaster(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  @Operation(summary = "Get all Paper Masters")
  public ResponseEntity<List<PaperMasterDTO>> getAllPaperMasters() {
    List<PaperMasterDTO> paperMasters = paperMasterService.getAllPaperMasters();
    return ResponseEntity.ok(paperMasters);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a Paper Master by ID")
  public ResponseEntity<PaperMasterDTO> getPaperMasterById(@PathVariable Long id) {
    PaperMasterDTO paperMaster = paperMasterService.getPaperMasterById(id);
    return ResponseEntity.ok(paperMaster);
  }

  @GetMapping("/search")
  @Operation(summary = "Search Paper Masters")
  public ResponseEntity<List<PaperMasterDTO>> searchPaperMasters(
      @RequestParam(required = false) String searchTerm,
      @RequestParam(required = false) Integer reelSize,
      @RequestParam(required = false) Integer gsm,
      @RequestParam(required = false) Integer bf) {
    List<PaperMasterDTO> paperMasters = paperMasterService.searchPaperMasters(searchTerm, reelSize, gsm, bf);
    return ResponseEntity.ok(paperMasters);
  }
}