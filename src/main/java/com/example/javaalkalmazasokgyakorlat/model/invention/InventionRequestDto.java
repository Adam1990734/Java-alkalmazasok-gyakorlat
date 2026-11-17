package com.example.javaalkalmazasokgyakorlat.model.invention;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class InventionRequestDto {
    @NotBlank(message = "The name is required!")
    @Size(max = 80, message = "The name cannot be bigger than 80 characters!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
