package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diagrams")
public class DiagramController {
    private final InventorService inventorService;

    public DiagramController(InventorService inventorService) { this.inventorService = inventorService; }

    @GetMapping("/withcount")
    public String viewWithCount(Model model) {
        model.addAttribute("inventorStats", inventorService.getInventorNameWithCountOfInventions());
        return "DiagramView/withCount";
    }

    @GetMapping("/timeline")
    public String viewDiagrams(Model model) {
        model.addAttribute("timelineData", inventorService.getInventorTimeLineStats());
        return "DiagramView/timeline";
    }
}
