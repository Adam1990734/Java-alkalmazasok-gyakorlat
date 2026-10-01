package com.example.javaalkalmazasokgyakorlat.repository;

import com.example.javaalkalmazasokgyakorlat.model.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> { }
