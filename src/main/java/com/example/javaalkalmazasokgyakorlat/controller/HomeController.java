package com.example.javaalkalmazasokgyakorlat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "UserView/home";
    }
    @GetMapping("/user")
    public String userPage() {
        return "UserView/user";
    }
    @GetMapping("/admin")
    public String adminPage() { return "UserView/admin"; }
}
