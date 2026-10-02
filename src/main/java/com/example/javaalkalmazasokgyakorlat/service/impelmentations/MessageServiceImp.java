package com.example.javaalkalmazasokgyakorlat.service.impelmentations;

import com.example.javaalkalmazasokgyakorlat.model.message.Message;
import com.example.javaalkalmazasokgyakorlat.model.message.MessageDto;
import com.example.javaalkalmazasokgyakorlat.model.message.MessageResponseDto;
import com.example.javaalkalmazasokgyakorlat.repository.MessageRepository;
import com.example.javaalkalmazasokgyakorlat.service.MessageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    private final MessageRepository repository;

    public MessageServiceImp(MessageRepository messageRepository) {
        this.repository = messageRepository;
    }

    @Override
    public List<MessageResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
    @Override
    public MessageResponseDto findById(Long id) {
        Message message = repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Message not found with id: " + id));
        return toDto(message);
    }
    @Override
    public MessageResponseDto create(MessageDto dto) {
        Message message = new Message();
        message.setContent(dto.getContent());
        message.setCreatedAt(LocalDateTime.now());
        Message savedMessage = repository.save(message);
        return toDto(savedMessage);
    }
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Message not found with id: " + id);
        }
        repository.deleteById(id);
    }
    @Override
    public List<MessageResponseDto> findAllDescByDatetime(int page, int size) {
        return repository.findAll(
                        PageRequest.of(
                                page,
                                size,
                                Sort.by("createdAt").descending()
                        )
                )
                .getContent()
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public Long countAll() { return repository.count(); }

    private MessageResponseDto toDto(Message message) {
        MessageResponseDto dto = new MessageResponseDto();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        dto.setUsername(
                message.getUser() == null ? "Anonymouse" : message.getUser().getUsername()
        );
        if(message.getUser() != null)
            dto.setUserId(message.getUser().getId());
        return dto;
    }
}
