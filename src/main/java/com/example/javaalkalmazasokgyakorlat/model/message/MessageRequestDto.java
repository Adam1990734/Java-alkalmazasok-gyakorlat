package com.example.javaalkalmazasokgyakorlat.model.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageRequestDto {
    @NotBlank(message = "The content cannot be empty!")
    @Size(max = 500, message = "The content max character length is 500!")
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
