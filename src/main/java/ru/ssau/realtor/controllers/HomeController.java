package ru.ssau.realtor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    static String NAME = "Vasya";

    @GetMapping({"/", "/home", "index"})
    public String home(Model model) {
        model.addAttribute("name", NAME);
        return "index";
    }
}
