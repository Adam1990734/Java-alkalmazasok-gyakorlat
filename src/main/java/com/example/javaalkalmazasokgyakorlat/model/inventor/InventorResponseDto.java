package com.example.javaalkalmazasokgyakorlat.model.inventor;

import java.util.Set;

public class InventorResponseDto {
    private Long id;
    private String name;
    private Integer bornat;
    private Integer diedat;
    private Set<Long> inventionIds;
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
    public Set<Long> getInventionIds() {
        return inventionIds;
    }
    public void setInventionIds(Set<Long> inventionIds) {
        this.inventionIds = inventionIds;
    }
}
