package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.message.MessageDto;
import com.example.javaalkalmazasokgyakorlat.model.message.MessageResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<MessageResponseDto> findAll();
    MessageResponseDto findById(Long id);
    MessageResponseDto create(MessageDto dto);
    void delete(Long id);
}
