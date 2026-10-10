package com.example.javaalkalmazasokgyakorlat.service.impelmentations;

import com.example.javaalkalmazasokgyakorlat.model.invention.Invention;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionResponseDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionShortDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorShortDto;
import com.example.javaalkalmazasokgyakorlat.repository.InventionRepository;
import com.example.javaalkalmazasokgyakorlat.repository.InventorRepository;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<InventionShortDto> findAllGetAsMinimal() {
        return repository.findAll()
                .stream()
                .map(this::toShortDto)
                .toList();
    }
    @Override
    public List<InventionShortDto> findAllGetAsMinimal(int page, int size) {
        return repository.findAll(
                        PageRequest.of(
                                page,
                                size
                        )
                )
                .stream()
                .map(this::toShortDto)
                .toList();
    }

    @Override
    public Long countAll() { return repository.count(); }

    private InventionResponseDto toDto(Invention invention) {
        InventionResponseDto dto = new InventionResponseDto();
        dto.setId(invention.getId());
        dto.setName(invention.getName());
        dto.setInventors(
                invention.getInventors()
                    .stream()
                    .map(i -> new InventorShortDto(i.getId(), i.getName()))
                    .collect(Collectors.toSet())
        );
        return dto;
    }
    private InventionShortDto toShortDto(Invention invention) {
        InventionShortDto dto = new InventionShortDto();
        dto.setId(invention.getId());
        dto.setName(invention.getName());
        return dto;
    }
    @Override
    public InventionDto toSimpDto(InventionResponseDto invention) {
        var dto = new InventionDto();
        dto.setName(invention.getName());
        return dto;
    }
}
