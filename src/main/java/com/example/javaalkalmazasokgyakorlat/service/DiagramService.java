package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorCountDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorTimeLineDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiagramService {
    List<InventorCountDto> getInventorNameWithCountOfInventions();
    List<InventorTimeLineDto> getInventorTimeLineStats();
}
