package com.example.javaalkalmazasokgyakorlat.model.invention;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public class InventionDto {
    @NotBlank
    @Size(max = 80)
    private String name;

    @NotEmpty
    private Set<Long> inventorIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getInventorIds() {
        return inventorIds;
    }

    public void setInventorIds(Set<Long> inventorIds) {
        this.inventorIds = inventorIds;
    }
}
