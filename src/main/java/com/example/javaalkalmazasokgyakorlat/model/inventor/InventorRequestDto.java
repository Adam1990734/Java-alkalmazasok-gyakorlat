package com.example.javaalkalmazasokgyakorlat.model.inventor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class InventorRequestDto {
    @NotBlank(message = "The name is required!")
    @Size(max = 40, message = "The name cannot be bigger than 40 characters!")
    private String name;

    @NotNull(message = "The born year is required!")
    private Integer bornat;

    private Integer diedat;

    private Set<Long> inventions;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBornat() {
        return bornat;
    }
    public void setBornat(Integer bornat) {
        this.bornat = bornat;
    }
    public Integer getDiedat() {
        return diedat;
    }
    public void setDiedat(Integer diedat) {
        this.diedat = diedat;
    }

    public Set<Long> getInventions() {
        return inventions;
    }

    public void setInventions(Set<Long> inventions) {
        this.inventions = inventions;
    }
}
