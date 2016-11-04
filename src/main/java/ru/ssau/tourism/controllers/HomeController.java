package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @Value("${app.author}")
    private String authorName;

    @RequestMapping({"/", "/home"})
    public String home(Model m) {
        m.addAttribute("author", authorName);
        return "index";
    }
}
