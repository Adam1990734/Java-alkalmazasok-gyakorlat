package com.example.javaalkalmazasokgyakorlat.controller.crud;

import com.example.javaalkalmazasokgyakorlat.model.invention.InventionShortDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/inventors")
public class InventorController {
    private final InventorService inventorService;
    private final InventionService inventionService;

    public InventorController(InventorService inventorService, InventionService inventionService) {
        this.inventorService = inventorService;
        this.inventionService = inventionService;
    }

    @GetMapping
    public String index(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        List<InventorResponseDto> inventors = inventorService.findAll(page, len);
        model.addAttribute("inventors", inventors);
        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventorService.countAll());
        return "InventorView/index";
    }
    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventor", inventor);
        return "InventorView/details";
    }
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("inventor", new InventorDto());
        model.addAttribute("otherInventions", this.inventionService.findAllGetAsMinimal(0, 20));
        return "InventorView/create";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute InventorDto dto) {
        InventorResponseDto created = inventorService.create(dto);
        return "redirect:/inventors";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventorId", inventor.getId());
        model.addAttribute("inventor", inventorService.toSimpDto(inventor));

        model.addAttribute("connectedInventions", inventor.getInventions());
        model.addAttribute("otherInventions", this.inventionService.findAllGetAsMinimal(0, 20));
        return "InventorView/update";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute InventorDto dto) {
        InventorResponseDto updated = inventorService.update(id, dto);
        return "redirect:/inventors";
    }
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventorId", inventor.getId());
        model.addAttribute("inventor", inventorService.toSimpDto(inventor));
        return "InventorView/delete";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        inventorService.delete(id);
        return "redirect:/inventors";
    }
}
