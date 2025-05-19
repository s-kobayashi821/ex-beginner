package com.example.controller;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {

    @Autowired
    private ServletContext application;

    @GetMapping("")
    public String index(){
        return "exam03";
    }

    @PostMapping("/result")
    public  String result(Integer itemCost1, Integer itemCost2, Integer itemCost3){
        Integer sumCost =  itemCost1 + itemCost2 + itemCost3;
        Integer sumCostWithTax = 110 * sumCost / 100;
        application.setAttribute("sumCost", sumCost);
        application.setAttribute("sumCostWithTax", sumCostWithTax);
        return "exam03-result";
    }
}
