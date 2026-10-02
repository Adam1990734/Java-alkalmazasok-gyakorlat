package com.example.javaalkalmazasokgyakorlat.controller;

import com.example.javaalkalmazasokgyakorlat.model.message.*;
import com.example.javaalkalmazasokgyakorlat.model.user.User;
import com.example.javaalkalmazasokgyakorlat.model.user.UserMoreDetails;
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
    public String saveMessage(@ModelAttribute MessageDto message, @AuthenticationPrincipal UserMoreDetails user, Model model) {
        var msg = new Message();
        msg.setContent(message.getContent());
        msg.setCreatedAt(LocalDateTime.now());

        Optional<User> connectedUser = userRepository.findById(user.getId());
        connectedUser.ifPresentOrElse(u -> {//Ha ismert akkor megy a userhez:
            u.addMessage(msg);
            userRepository.save(u);
        }, () -> {//Ha nem ismert megy statikus taghoz (ha nincs benne seed-eld újra vagy nézd meg a fájlt):
            Optional<User> anon = userRepository.findByUsername("ANONYMOUS");
            anon.ifPresent(a -> {
                a.addMessage(msg);
                userRepository.save(a);
            });
        });
        return "redirect:/";
    }
}
