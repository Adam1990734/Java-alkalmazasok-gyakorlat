package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventorService {
    List<InventorResponseDto> findAll();
    List<InventorResponseDto> findAll(int page, int size);
    InventorResponseDto findById(Long id);
    InventorResponseDto create(InventorDto dto);
    InventorResponseDto update(Long id, InventorDto dto);
    void delete(Long id);

    Long countAll();
}
