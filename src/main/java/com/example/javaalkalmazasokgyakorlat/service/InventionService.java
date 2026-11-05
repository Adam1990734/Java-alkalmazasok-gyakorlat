package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.invention.*;

import java.util.List;

public interface InventionService {
    List<InventionResponseDto> findAll();
    List<InventionResponseDto> findAll(int page, int size);
    List<InventionShortDto> findAllGetAsMinimal();
    List<InventionShortDto> findAllGetAsMinimal(int page, int size);
    InventionResponseDto findById(Long id);
    InventionResponseDto create(InventionRequestDto dto);
    InventionResponseDto update(Long id, InventionRequestDto dto);
    void delete(Long id);

    Long countAll();

    InventionRequestDto toSimpDto(InventionResponseDto invention);

    Boolean existById(Long Id);
}
