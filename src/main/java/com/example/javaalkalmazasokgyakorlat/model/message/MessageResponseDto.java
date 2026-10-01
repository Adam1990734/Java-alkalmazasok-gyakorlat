package com.example.javaalkalmazasokgyakorlat.model.message;

import java.time.LocalDateTime;

public class MessageResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long userId;
    private String username;

    public MessageResponseDto() {}
    public MessageResponseDto(
            Long id,
            String content,
            LocalDateTime createdAt,
            Long userId,
            String username
    ) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
        this.username = username;
    }

    public Long getId() {
        return id;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Long getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
