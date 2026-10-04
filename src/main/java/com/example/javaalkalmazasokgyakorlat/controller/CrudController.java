package com.example.javaalkalmazasokgyakorlat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/crud")
public class CrudController {
    //Simlán csak form-al
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
        return switch (chosen) {
            case "Inventor" -> "redirect: /inventor";
            case "Invention" -> "redirect: /invention";
            default -> "CrudView/index";
        };
    }
}
