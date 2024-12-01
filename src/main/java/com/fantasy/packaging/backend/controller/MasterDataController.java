package com.fantasy.packaging.backend.controller;

import com.fantasy.packaging.backend.entity.Mill;
import com.fantasy.packaging.backend.dto.PaperMasterDTO;
import com.fantasy.packaging.backend.entity.ShadeMaster;
import com.fantasy.packaging.backend.service.MillService;
import com.fantasy.packaging.backend.service.PaperMasterService;
import com.fantasy.packaging.backend.service.ShadeMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master-data")
@RequiredArgsConstructor
public class MasterDataController {

  private final MillService millService;
  private final PaperMasterService paperMasterService;
  private final ShadeMasterService shadeMasterService;

  @GetMapping("/paper-names")
  public ResponseEntity<List<String>> getPaperNames() {
    return ResponseEntity.ok(
        paperMasterService.getAllPaperMasters().stream()
            .map(PaperMasterDTO::getPartName)
            .collect(Collectors.toList())
    );
  }

  @GetMapping("/mill-names")
  public ResponseEntity<List<String>> getMillNames() {
    return ResponseEntity.ok(
        millService.getAllMills().stream()
            .map(Mill::getMillName)
            .collect(Collectors.toList())
    );
  }

  @GetMapping("/shades")
  public ResponseEntity<List<String>> getShades() {
    return ResponseEntity.ok(
        shadeMasterService.getAllShades().stream()
            .map(ShadeMaster::getShadeName)
            .collect(Collectors.toList())
    );
  }
}