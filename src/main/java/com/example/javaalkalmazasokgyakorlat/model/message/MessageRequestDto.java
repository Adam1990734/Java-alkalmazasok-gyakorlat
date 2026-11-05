package com.example.javaalkalmazasokgyakorlat.model.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageRequestDto {
    @NotBlank
    @Size(max = 500)
    private String content;

    private Long userId;

    public MessageRequestDto() {}
    public MessageRequestDto(String content, Long userId) {
        this.content = content;
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
