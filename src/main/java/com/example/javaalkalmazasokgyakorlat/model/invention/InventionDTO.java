package com.example.javaalkalmazasokgyakorlat.model.invention;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class InventionDTO {
    private Long id;
    private String name;
    private Set<Long> inventorids;

    public InventionDTO(Long id, String name, Set<Long> inventorids) {
        this.id = id;
        this.name = name;
        this.inventorids = inventorids;
    }
    public InventionDTO(final Invention invention) {
        this.id = invention.getId();
        this.name = invention.getName();
        this.inventorids = invention.getInventors().stream().map(inv -> inv.getId()).collect(Collectors.toSet());
    }

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

    public Set<Long> getInventorids() {
        return inventorids;
    }

    public void setInventorids(Set<Long> inventorids) {
        this.inventorids = inventorids;
    }
}
