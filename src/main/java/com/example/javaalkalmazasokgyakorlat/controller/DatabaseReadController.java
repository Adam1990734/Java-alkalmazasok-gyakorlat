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
    //Default-on az index oldal de terveim szerinta form get-el választ:
    @GetMapping
    public String index(
            @RequestParam(name = "chosen", defaultValue = "None", required = false) String chosen,
            Model model
    ) {
        model.addAttribute("chosen", chosen);
        model.addAttribute("availableTables", new String[]{
                "Inventor",
                "Invention"
        });
        switch (chosen) {
            case "Inventor": return "redirect: /show/inventor";
            case "Invention": return "redirect: /show/invention";
            default: return "DatabaseReadView/index";
        }
    }
    @GetMapping("/inventor")
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
        return "DatabaseReadView/inventor";
    }
    @GetMapping("/invention")
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
        return "DatabaseReadView/invention";
    }
}
