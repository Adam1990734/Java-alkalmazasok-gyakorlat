package com.example.javaalkalmazasokgyakorlat.controller.restcrud;

import com.example.javaalkalmazasokgyakorlat.model.invention.InventionRequestDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionResponseDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionShortDto;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventions")
public class InventionRestController {
    private final InventionService inventionService;
    public InventionRestController(InventionService inventionService) {
        this.inventionService = inventionService;
    }
    @GetMapping
    public ResponseEntity<List<InventionResponseDto>> findAll() {
        List<InventionResponseDto> inventions = inventionService.findAll();
        return ResponseEntity.ok(inventions);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventionResponseDto> findById(@PathVariable Long id) {
        if(!inventionService.existById(id))
            return ResponseEntity.notFound().build();
        InventionResponseDto invention = inventionService.findById(id);
        return ResponseEntity.ok(invention);
    }
    @PostMapping
    public ResponseEntity<InventionResponseDto> create(@RequestBody InventionRequestDto dto) {
        InventionResponseDto created = inventionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<InventionResponseDto> update(@PathVariable Long id, @RequestBody InventionRequestDto dto) {
        if(!inventionService.existById(id))
            return ResponseEntity.notFound().build();
        InventionResponseDto updated = inventionService.update(id, dto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!inventionService.existById(id))
            return ResponseEntity.notFound().build();
        inventionService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/suggest")
    public ResponseEntity<List<InventionShortDto>> suggestByName(
            @RequestParam(name = "name", defaultValue = "") String name
    ) {
        if(name.isEmpty())
            return ResponseEntity.ok(
                    inventionService.findAllGetAsMinimal(0, 10)
            );
        var inventors = inventionService.findSuggestionsByNameAsMinimal(name, 10);
        return ResponseEntity.ok(inventors);
    }
}
