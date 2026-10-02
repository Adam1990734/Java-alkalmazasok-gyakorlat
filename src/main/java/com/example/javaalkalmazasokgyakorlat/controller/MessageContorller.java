package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.repository.MessageRepository;
import com.example.javaalkalmazasokgyakorlat.service.MessageService;
import jakarta.persistence.Table;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/message")
public class MessageContorller {
    private final MessageService messageService;

    public MessageContorller(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String index(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "len", required = false, defaultValue = "20") int len,
            Model model) {
        model.addAttribute("messages", messageService.findAllDescByDatetime(page, len));
        model.addAttribute("len", len);
        model.addAttribute("lastpage", page);
        model.addAttribute("allcount", messageService.countAll());
        return "MessageView/index";
    }
}
