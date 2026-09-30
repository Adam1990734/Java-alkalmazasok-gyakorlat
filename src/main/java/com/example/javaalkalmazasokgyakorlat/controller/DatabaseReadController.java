package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/show")
public class DatabaseReadController {
    private final InventorService inventorService;
    private final InventionService inventionService;

    public DatabaseReadController(InventorService inventorService, InventionService inventionService) {
        this.inventorService = inventorService;
        this.inventionService = inventionService;
    }

    @GetMapping()
    public String index() {
        //Ide kell egy kezdő view!
        return "/";
    }
    @GetMapping("/inventor")
    public String viewInventors() {
        return "/";
    }
    @GetMapping("/invention")
    public String viewInventions() {
        return "/";
    }
}
