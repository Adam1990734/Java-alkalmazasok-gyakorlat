package com.example.javaalkalmazasokgyakorlat.controller.crud;

import com.example.javaalkalmazasokgyakorlat.model.invention.InventionDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionResponseDto;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/invention")
public class InventionController {
    private final InventionService inventionService;
    public InventionController(InventionService inventionService) {
        this.inventionService = inventionService;
    }

    @GetMapping
    public String index(Model model) {
        List<InventionResponseDto> inventions = inventionService.findAll();
        model.addAttribute("inventions", inventions);
        return "InventionView/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        InventionResponseDto invention = inventionService.findById(id);
        model.addAttribute("invention", invention);
        return "InventionView/details";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("invention", new InventionDto());
        return "InventionView/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute InventionDto dto) {
        InventionResponseDto created = inventionService.create(dto);
        return "redirect:/invention";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        InventionResponseDto invention = inventionService.findById(id);
        model.addAttribute("invention", invention);
        return "InventionView/update";
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute InventionDto dto) {
        InventionResponseDto updated = inventionService.update(id, dto);
        return "redirect:/invention";
    }
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        InventionResponseDto invention = inventionService.findById(id);
        model.addAttribute("invention", invention);
        return "InventionView/delete";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        inventionService.delete(id);
        return "redirect:/invention";
    }
}
