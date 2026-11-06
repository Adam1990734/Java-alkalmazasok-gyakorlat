package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/show")
public class DatabaseReadController {
    private final InventorService inventorService;
    private final InventionService inventionService;

    public DatabaseReadController(InventorService inventorService, InventionService inventionService) {
        this.inventorService = inventorService;
        this.inventionService = inventionService;
    }

    @GetMapping("/inventors")
    public String viewInventors(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        var inventors = inventorService.findAll(page, len);
        model.addAttribute("inventors", inventors);

        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventorService.countAll());
        model.addAttribute("datasize", inventors.size());
        model.addAttribute("hasOperations", false);
        return "DatabaseReadView/inventor";
    }
    @GetMapping("/inventions")
    public String viewInventions(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        var inventions = inventionService.findAll(page, len);
        model.addAttribute("inventions", inventions);

        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventionService.countAll());
        model.addAttribute("datasize", inventions.size());
        model.addAttribute("hasOperations", false);
        return "DatabaseReadView/invention";
    }
    @GetMapping("/all")
    public String viewAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        var all = inventorService.findAll(page, len);
        model.addAttribute("all", all);

        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventorService.countAll());
        model.addAttribute("datasize", all.size());
        model.addAttribute("hasOperations", false);
        return "DatabaseReadView/all";
    }
}
