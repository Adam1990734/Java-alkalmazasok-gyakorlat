package com.example.javaalkalmazasokgyakorlat.repository;

import com.example.javaalkalmazasokgyakorlat.model.invention.Invention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventionRepository extends JpaRepository<Invention, Long> { }
