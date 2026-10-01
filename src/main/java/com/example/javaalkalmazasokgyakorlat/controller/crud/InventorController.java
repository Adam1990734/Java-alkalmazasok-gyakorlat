package com.example.javaalkalmazasokgyakorlat.controller.crud;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventor")
public class InventorController {
    private final InventorService inventorService;
    public InventorController(InventorService inventorService) {
        this.inventorService = inventorService;
    }

    @GetMapping
    public String index(Model model) {
        List<InventorResponseDto> inventors = inventorService.findAll();
        model.addAttribute("inventor", inventors);
        return "InventorView/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("invention", inventor);
        return "InventorView/details";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("inventor", new InventorDto());
        return "InventorView/create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute InventorDto dto) {
        InventorResponseDto created = inventorService.create(dto);
        return "redirect:/inventor";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventor", inventor);
        return "InventorView/update";
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute InventorDto dto) {
        InventorResponseDto updated = inventorService.update(id, dto);
        return "redirect:/inventor";
    }
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventor", inventor);
        return "InventorView/delete";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        inventorService.delete(id);
        return "redirect:/inventor";
    }
}
