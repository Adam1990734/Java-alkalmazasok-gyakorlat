package com.example.javaalkalmazasokgyakorlat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/crud")
public class CrudController {
    @GetMapping
    public String index() {
        //Ide fog jönni az hogy a megfelelő rest crud fele rányít majd a rendszer
        return "/";
    }
}
