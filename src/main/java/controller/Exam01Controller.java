package controller;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
@Repository("/exam01")
public class Exam01Controller {

    @GetMapping("")
    public String index(Model model){
        return "exam01";
    }

    @PostMapping("/result")
    public String result(String name, Model model){
        return "exam01-result";
    }
}
