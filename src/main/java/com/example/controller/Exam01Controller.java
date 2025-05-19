package com.example.controller;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
@RequestMapping("/exam01")
public class Exam01Controller {

    @GetMapping("")
    public String index(Model model){
        return "exam01";
    }

    @PostMapping("/result")
    public String result(String name, Model model){
        model.addAttribute("name", name);
        return "exam01-result";
    }
}
