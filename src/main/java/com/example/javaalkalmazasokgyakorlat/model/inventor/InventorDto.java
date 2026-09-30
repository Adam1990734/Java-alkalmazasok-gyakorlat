package com.example.javaalkalmazasokgyakorlat.model.inventor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InventorDto {
    @NotBlank
    @Size(max = 40)
    private String name;
    @NotNull
    private Integer bornat;
    private Integer diedat;
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
}
