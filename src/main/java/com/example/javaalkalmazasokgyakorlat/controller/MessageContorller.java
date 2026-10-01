package com.example.javaalkalmazasokgyakorlat.controller;


import com.example.javaalkalmazasokgyakorlat.repository.MessageRepository;
import com.example.javaalkalmazasokgyakorlat.service.MessageService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/message")
public class MessageContorller {
    private final MessageService messageService;

    public MessageContorller(MessageService messageService) { this.messageService = messageService; }

    @GetMapping
    public String index(@RequestParam(defaultValue = "0") int page, @RequestParam(name = "len", defaultValue = "50") int len, Model model) {
        //Cél a lapozhatóság lenne (ezt majd lehet kéne a másik táblákra is)
        return "/";
    }
}
