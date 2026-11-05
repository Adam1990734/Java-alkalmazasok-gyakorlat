package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.inventor.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventorService {
    List<InventorResponseDto> findAll();
    List<InventorResponseDto> findAll(int page, int size);
    List<InventorShortDto> findAllGetAsMinimal();
    List<InventorShortDto> findAllGetAsMinimal(int page, int size);
    InventorResponseDto findById(Long id);
    InventorResponseDto create(InventorRequestDto dto);
    InventorResponseDto update(Long id, InventorRequestDto dto);
    void delete(Long id);

    Long countAll();
    InventorRequestDto toSimpDto(InventorResponseDto inventor);

    List<InventorCountDto> getInventorNameWithCountOfInventions();
    List<InventorTimeLineDto> getInventorTimeLineStats();

    Boolean existById(Long Id);
}
