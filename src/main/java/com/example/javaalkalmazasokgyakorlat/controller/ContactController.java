package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.model.message.*;
import com.example.javaalkalmazasokgyakorlat.model.user.User;
import com.example.javaalkalmazasokgyakorlat.model.user.UserMoreDetails;
import com.example.javaalkalmazasokgyakorlat.repository.MessageRepository;
import com.example.javaalkalmazasokgyakorlat.repository.UserRepository;
import com.example.javaalkalmazasokgyakorlat.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/contact")
public class ContactController {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ContactController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("message", new MessageDto());
        return "MessageView/index";
    }
    @PostMapping
    public String saveMessage(@ModelAttribute MessageDto message, @AuthenticationPrincipal UserMoreDetails user, Model model) {
        Message msg = new Message();
        msg.setContent(message.getContent());
        msg.setCreatedAt(LocalDateTime.now());

        Optional<User> connectedUser = userRepository.findById(user.getId());
        connectedUser.ifPresentOrElse(u -> {//Ha ismert akkor megy a userhez:
            u.addMessage(msg);
            userRepository.save(u);
            //Ha nincs akkor NULL hatékonyabb!
        }, () -> messageRepository.save(msg));
        return "redirect:/";
    }
}
