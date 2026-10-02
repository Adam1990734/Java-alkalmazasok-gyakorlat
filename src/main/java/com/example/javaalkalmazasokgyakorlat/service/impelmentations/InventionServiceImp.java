package com.example.javaalkalmazasokgyakorlat.service.impelmentations;

import com.example.javaalkalmazasokgyakorlat.model.invention.Invention;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionResponseDto;
import com.example.javaalkalmazasokgyakorlat.repository.InventionRepository;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventionServiceImp implements InventionService {
    private final InventionRepository repository;
    public InventionServiceImp(InventionRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<InventionResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
    @Override
    public InventionResponseDto findById(Long id) {
        Invention invention = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invention not found"));
        return toDto(invention);
    }
    @Override
    public InventionResponseDto create(InventionDto dto) {
        Invention invention = new Invention();
        invention.setName(dto.getName());
        return toDto(repository.save(invention));
    }
    @Override
    public InventionResponseDto update(Long id, InventionDto dto) {
        Invention invention = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invention not found"));
        invention.setName(dto.getName());
        return toDto(repository.save(invention));
    }
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<InventionResponseDto> findAll(int page, int size) {
        return repository.findAll(
                        PageRequest.of(
                                page,
                                size
                        )
                )
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Long countAll() { return repository.count(); }

    private InventionResponseDto toDto(Invention invention) {
        InventionResponseDto dto = new InventionResponseDto();
        dto.setId(invention.getId());
        dto.setName(invention.getName());
        Set<Long> inventorIds = invention.getInventors()
                .stream()
                .map(i -> i.getId())
                .collect(Collectors.toSet());
        dto.setInventorIds(inventorIds);
        return dto;
    }
}
