package com.example.javaalkalmazasokgyakorlat.repository;

import com.example.javaalkalmazasokgyakorlat.model.inventor.Inventor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventorRepository extends JpaRepository<Inventor, Long> { }
