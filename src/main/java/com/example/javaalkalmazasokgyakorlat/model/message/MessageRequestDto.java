package com.example.javaalkalmazasokgyakorlat.model.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageRequestDto {
    @NotBlank
    @Size(max = 500)
    private String content;

    public MessageRequestDto() {}
    public MessageRequestDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
