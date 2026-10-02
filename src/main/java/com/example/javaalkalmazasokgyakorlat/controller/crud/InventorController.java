package com.example.javaalkalmazasokgyakorlat.controller.crud;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.repository.InventorRepository;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inventor")
public class InventorController {
    private final InventorService inventorService;
    private final InventorRepository inventorRepository;
    public InventorController(InventorService inventorService, InventorRepository inventorRepository) {
        this.inventorService = inventorService;
        this.inventorRepository = inventorRepository;
    }

    @GetMapping
    public String index(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        List<InventorResponseDto> inventors = inventorService.findAll(page, len);
        model.addAttribute("inventor", inventors);
        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventorService.countAll());
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
    public String create(@Valid @ModelAttribute InventorDto dto) {
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
    public String update(@PathVariable Long id, @Valid @ModelAttribute InventorDto dto) {
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
