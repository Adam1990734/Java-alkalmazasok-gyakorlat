package com.example.javaalkalmazasokgyakorlat.service;

import com.example.javaalkalmazasokgyakorlat.model.message.MessageRequestDto;
import com.example.javaalkalmazasokgyakorlat.model.message.MessageResponseDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface MessageService {
    List<MessageResponseDto> findAll();
    List<MessageResponseDto> findAllDescByDatetime(int page, int size);
    MessageResponseDto findById(Long id);
    MessageResponseDto create(MessageRequestDto dto);
    void delete(Long id);

    Long countAll();
}