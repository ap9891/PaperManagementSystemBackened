package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.entity.ShadeMaster;
import com.fantasy.packaging.backend.service.ShadeMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shades")
@CrossOrigin(origins = "http://localhost:3001")
public class ShadeMasterController {
  @Autowired
  private ShadeMasterService shadeMasterService;

  @GetMapping
  public List<ShadeMaster> getAllShades() {
    return shadeMasterService.getAllShades();
  }

  @PostMapping
  public ShadeMaster createShade(@Valid @RequestBody ShadeMaster shade) {
    return shadeMasterService.createShade(shade);
  }

  @PutMapping("/{shadeId}")
  public ShadeMaster updateShade(@PathVariable String shadeId, @Valid @RequestBody ShadeMaster shadeDetails) {
    return shadeMasterService.updateShade(shadeId, shadeDetails);
  }

  @DeleteMapping("/{shadeId}")
  public ResponseEntity<?> deleteShade(@PathVariable String shadeId) {
    shadeMasterService.deleteShade(shadeId);
    return ResponseEntity.ok().build();
  }
}