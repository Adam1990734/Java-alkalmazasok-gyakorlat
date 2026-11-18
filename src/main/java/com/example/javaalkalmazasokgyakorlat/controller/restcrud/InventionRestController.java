package com.example.javaalkalmazasokgyakorlat.controller.restcrud;

import com.example.javaalkalmazasokgyakorlat.model.invention.InventionRequestDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionResponseDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionShortDto;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> create(@Valid @RequestBody InventionRequestDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));
            return ResponseEntity.badRequest().body(errors);
        }

        InventionResponseDto created = inventionService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody InventionRequestDto dto,
            BindingResult bindingResult
    ) {
        if(!inventionService.existById(id))
            return ResponseEntity.notFound().build();
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult
                    .getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));
            return ResponseEntity.badRequest().body(errors);
        }

        InventionResponseDto updated = inventionService.update(id, dto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!inventionService.existById(id))
            return ResponseEntity.notFound().build();
        inventionService.delete(id);
        return ResponseEntity.noContent().build();
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
