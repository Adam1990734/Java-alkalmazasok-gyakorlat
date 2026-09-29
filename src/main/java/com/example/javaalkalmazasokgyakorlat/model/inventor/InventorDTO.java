package com.example.javaalkalmazasokgyakorlat.model.inventor;

import com.example.javaalkalmazasokgyakorlat.model.invention.Invention;

import java.util.Set;
import java.util.stream.Collectors;

public class InventorDTO {
    private Long id;
    private String name;
    private Set<Long> inventionids;

    public InventorDTO(Long id, String name, Set<Long> inventionids) {
        this.id = id;
        this.name = name;
        this.inventionids = inventionids;
    }

    public InventorDTO(final Inventor inventor) {
        this.id = inventor.getId();
        this.name = inventor.getName();
        this.inventionids = inventor.getInventions().stream().map(inv -> inv.getId()).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Long> getInventionids() {
        return inventionids;
    }

    public void setInventionids(Set<Long> inventionids) {
        this.inventionids = inventionids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
