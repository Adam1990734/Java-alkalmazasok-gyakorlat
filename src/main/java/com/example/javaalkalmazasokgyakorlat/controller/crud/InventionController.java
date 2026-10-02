package com.example.javaalkalmazasokgyakorlat.controller.crud;

import com.example.javaalkalmazasokgyakorlat.model.invention.InventionDto;
import com.example.javaalkalmazasokgyakorlat.model.invention.InventionResponseDto;
import com.example.javaalkalmazasokgyakorlat.repository.InventionRepository;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/invention")
public class InventionController {
    private final InventionService inventionService;
    private final InventionRepository inventionRepository;

    public InventionController(InventionService inventionService, InventionRepository inventionRepository) {
        this.inventionService = inventionService;
        this.inventionRepository = inventionRepository;
    }

    @GetMapping
    public String index(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        List<InventionResponseDto> inventions = inventionService.findAll(page, len);
        model.addAttribute("inventions", inventions);
        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventionService.countAll());
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
    public String create(@Valid @ModelAttribute InventionDto dto) {
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
    public String update(@PathVariable Long id, @Valid @ModelAttribute InventionDto dto) {
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
