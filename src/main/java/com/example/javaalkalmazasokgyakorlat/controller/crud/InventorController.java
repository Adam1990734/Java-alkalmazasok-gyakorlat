package com.example.javaalkalmazasokgyakorlat.controller.crud;

import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorRequestDto;
import com.example.javaalkalmazasokgyakorlat.model.inventor.InventorResponseDto;
import com.example.javaalkalmazasokgyakorlat.service.InventionService;
import com.example.javaalkalmazasokgyakorlat.service.InventorService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/crud/inventors")
public class InventorController {
    private final InventorService inventorService;

    public InventorController(InventorService inventorService) {
        this.inventorService = inventorService;
    }

    @GetMapping
    public String index(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "len", defaultValue = "20", required = false) int len,
            Model model
    ) {
        List<InventorResponseDto> inventors = inventorService.findAll(page, len);
        model.addAttribute("inventors", inventors);
        //Lapozáshoz (pager-ben):
        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", inventorService.countAll());
        model.addAttribute("datasize", inventors.size());
        model.addAttribute("hasOperations", true);
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
        model.addAttribute("inventor", new InventorRequestDto());
        model.addAttribute("isUpdate", false);
        return "InventorView/create";
    }
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute InventorRequestDto dto) {
        InventorResponseDto created = inventorService.create(dto);
        return "redirect:/crud/inventors";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventorId", inventor.getId());
        model.addAttribute("inventor", inventorService.toSimpDto(inventor));

        model.addAttribute("connectedInventions", inventor.getInventions());
        model.addAttribute("isUpdate", true);
        return "InventorView/update";
    }
    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute InventorRequestDto dto) {
        InventorResponseDto updated = inventorService.update(id, dto);
        return "redirect:/crud/inventors";
    }
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable Long id, Model model) {
        InventorResponseDto inventor = inventorService.findById(id);
        model.addAttribute("inventorId", inventor.getId());
        model.addAttribute("inventor", inventorService.toSimpDto(inventor));
        return "InventorView/delete";
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        inventorService.delete(id);
        return "redirect:/crud/inventors";
    }
}
