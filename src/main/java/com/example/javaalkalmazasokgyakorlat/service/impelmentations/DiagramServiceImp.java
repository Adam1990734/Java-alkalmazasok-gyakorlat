package com.example.javaalkalmazasokgyakorlat.service.impelmentations;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorCountDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorTimeLineDto;
import com.example.javaalkalmazasokgyakorlat.repository.InventionRepository;
import com.example.javaalkalmazasokgyakorlat.repository.InventorRepository;
import com.example.javaalkalmazasokgyakorlat.service.DiagramService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagramServiceImp implements DiagramService {
    private final InventorRepository repository;

    public DiagramServiceImp(InventorRepository repository, InventionRepository inventionRepository) { this.repository = repository; }

    @Override
    public List<InventorCountDto> getInventorNameWithCountOfInventions() {
        return repository.getStatistics();
    }
    @Override
    public List<InventorTimeLineDto> getInventorTimeLineStats() { return  repository.getTimeLineData(); }
}
