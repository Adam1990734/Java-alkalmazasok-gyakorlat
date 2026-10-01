package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.model.message.Message;
import com.example.javaalkalmazasokgyakorlat.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final MessageService messageService;

    public ContactController(MessageService messageService) { this.messageService = messageService; }

    @GetMapping
    public String index(Model model) {
        //Ide valami egyszerű téma kéne és abba kéne beágyazni a form-ot (külön html-be gondoltam tenni hogy átlátható legyen)
        model.addAttribute("message", new Message());
        //Úgy gondoltam hogy majd az üzenete user alpján bekötjük
        return "/";
    }
    @PostMapping
    public String saveMessage(Model model) {
        //Visszajelzést gondoltam a usernek és után ott legyen egy visszaugrás a főoldalra
        model.addAttribute("resultOfSave", true);
        return "/";
    }
}
