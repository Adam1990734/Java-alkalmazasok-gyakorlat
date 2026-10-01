package com.example.javaalkalmazasokgyakorlat.repository;

import com.example.javaalkalmazasokgyakorlat.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByCreatedAtAfterOrderByCreatedAtAsc(LocalDateTime createdAt);
}
