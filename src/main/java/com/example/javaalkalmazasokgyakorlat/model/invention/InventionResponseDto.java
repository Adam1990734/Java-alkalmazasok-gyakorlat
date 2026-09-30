package com.example.javaalkalmazasokgyakorlat.model.invention;

import java.util.Set;

public class InventionResponseDto {
    private Long id;
    private String name;
    private Set<Long> inventorIds;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
