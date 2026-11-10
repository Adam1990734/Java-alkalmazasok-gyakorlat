package com.example.javaalkalmazasokgyakorlat.controller.diagram;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorCountDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorTimeLineDto;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/diagrams")
public class DiagramApiController {
    private final InventorService inventorService;

    public DiagramApiController(InventorService inventorService) { this.inventorService = inventorService; }

    @GetMapping("/withcount")
    public ResponseEntity<List<InventorCountDto>> withCount() {
        return ResponseEntity.ok(
           this.inventorService.getInventorNameWithCountOfInventions()
        );
    }

    @GetMapping("/timeline")
    public ResponseEntity<List<InventorTimeLineDto>> timeline() {
        return ResponseEntity.ok(
           this.inventorService.getInventorTimeLineStats()
        );
    }
}
