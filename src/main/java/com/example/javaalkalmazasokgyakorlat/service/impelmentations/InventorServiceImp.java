package com.example.javaalkalmazasokgyakorlat.service.impelmentations;

import com.example.javaalkalmazasokgyakorlat.model.invention.Invention;
import com.example.javaalkalmazasokgyakorlat.model.inventor.Inventor;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.repository.InventorRepository;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventorServiceImp implements InventorService {
    private final InventorRepository repository;

    public InventorServiceImp(InventorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<InventorResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public InventorResponseDto findById(Long id) {
        Inventor inventor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventor not found"));
        return toDto(inventor);
    }

    @Override
    public InventorResponseDto create(InventorDto dto) {
        Inventor inventor = new Inventor();
        inventor.setName(dto.getName());
        inventor.setBornat(dto.getBornat());
        if (dto.getDiedat() != null) {
            inventor.setDiedat(dto.getDiedat());
        }
        return toDto(repository.save(inventor));
    }

    @Override
    public InventorResponseDto update(
            Long id,
            InventorDto dto) {
        Inventor inventor = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Inventor not found"));
        inventor.setName(dto.getName());
        inventor.setBornat(dto.getBornat());
        if (dto.getDiedat() != null) {
            inventor.setDiedat(dto.getDiedat());
        }
        return toDto(repository.save(inventor));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<InventorResponseDto> findAll(int page, int size) {
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

    private InventorResponseDto toDto(Inventor inventor) {
        InventorResponseDto dto = new InventorResponseDto();
        dto.setId(inventor.getId());
        dto.setName(inventor.getName());
        dto.setBornat(inventor.getBornat());
        dto.setDiedat(inventor.getDiedat());
        dto.setInventionIds(
                inventor.getInventions()
                        .stream()
                        .map(Invention::getId)
                        .collect(Collectors.toSet())
        );
        return dto;
    }
}
