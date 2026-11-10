package com.example.javaalkalmazasokgyakorlat.controller.diagram;

import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diagrams")
public class DiagramController {
    @GetMapping("/withcount")
    public String viewWithCount() { return "DiagramView/withCount"; }

    @GetMapping("/timeline")
    public String viewDiagrams() { return "DiagramView/timeline"; }
}
