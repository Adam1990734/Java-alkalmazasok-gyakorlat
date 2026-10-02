package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.invention.*;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;

import java.util.List;

public interface InventionService {
    List<InventionResponseDto> findAll();
    List<InventionResponseDto> findAll(int page, int size);
    InventionResponseDto findById(Long id);
    InventionResponseDto create(InventionDto dto);
    InventionResponseDto update(Long id, InventionDto dto);
    void delete(Long id);

    Long countAll();
}
