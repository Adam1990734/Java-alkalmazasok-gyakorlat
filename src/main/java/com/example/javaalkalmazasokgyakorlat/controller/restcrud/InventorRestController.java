package com.example.javaalkalmazasokgyakorlat.controller.restcrud;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorRequestDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventors")
public class InventorRestController {
    private final InventorService inventorService;
    public InventorRestController(InventorService inventorService) {
        this.inventorService = inventorService;
    }
    @GetMapping
    public ResponseEntity<List<InventorResponseDto>> findAll() {
        List<InventorResponseDto> inventors = inventorService.findAll();
        return ResponseEntity.ok(inventors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InventorResponseDto> findById(@PathVariable Long id) {
        if(!inventorService.existById(id))
            return ResponseEntity.notFound().build();
        InventorResponseDto inventor = inventorService.findById(id);
        return ResponseEntity.ok(inventor);
    }
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody InventorRequestDto dto, BindingResult bindingResult) {
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

        InventorResponseDto created = inventorService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(created);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody InventorRequestDto dto,
            BindingResult bindingResult
    ) {
        if(!inventorService.existById(id))
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

        InventorResponseDto updated = inventorService.update(id, dto);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!inventorService.existById(id))
            return ResponseEntity.notFound().build();
        inventorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
