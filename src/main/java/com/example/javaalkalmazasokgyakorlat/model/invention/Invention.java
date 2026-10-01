package com.example.javaalkalmazasokgyakorlat.model.invention;

import com.example.javaalkalmazasokgyakorlat.model.inventor.Inventor;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "talalmany")
public class Invention {
    @Id
    @Column(name = "tkod")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "talnev", length = 80, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "inventions", fetch = FetchType.LAZY)
    Set<Inventor> inventors = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Inventor> getInventors() {
        return inventors;
    }
}
