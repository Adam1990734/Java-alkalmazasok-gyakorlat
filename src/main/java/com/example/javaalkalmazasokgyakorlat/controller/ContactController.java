package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.model.message.*;
import com.example.javaalkalmazasokgyakorlat.model.user.UserMoreDetails;
import com.example.javaalkalmazasokgyakorlat.repository.UserRepository;
import com.example.javaalkalmazasokgyakorlat.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final MessageService messageService;
    private final UserRepository userRepository;

    public ContactController(MessageService messageService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", new MessageDto());
        return "MessageView/index";
    }
    @PostMapping
    @ResponseBody
    public Message saveMessage(@RequestParam MessageDto message, @AuthenticationPrincipal UserMoreDetails user, Model model) {
        var msg = new Message();
        msg.setContent(message.getContent());
        msg.setCreatedAt(LocalDateTime.now());
        user.getUser().getMessages().add(msg);
        return msg;
    }
}
