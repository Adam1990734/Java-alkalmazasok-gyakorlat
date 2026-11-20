package com.example.javaalkalmazasokgyakorlat.model.inventor;

import jakarta.validation.constraints.*;

import java.util.Set;

public class InventorRequestDto {
    @NotBlank(message = "The name is required!")
    @Size(max = 40, message = "The name cannot be bigger than 40 characters!")
    private String name;

    @NotNull(message = "The born year is required!")
    @Min(value = 1, message = "The 1 is the minimum year!")
    @Max(value = 2026, message = "The 2026 is the maximum year!")
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
