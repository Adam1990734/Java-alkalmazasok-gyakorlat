package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.invention.*;

import java.util.List;

public interface InventionService {
    List<InventionResponseDto> findAll();
    InventionResponseDto findById(Long id);
    InventionResponseDto create(InventionDto dto);
    InventionResponseDto update(Long id, InventionDto dto);
    void delete(Long id);
}
