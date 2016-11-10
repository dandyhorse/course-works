package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.tourism.entities.Tourist;
import ru.ssau.tourism.services.DataBaseService;

@Controller
@RequestMapping("/tourists")
public class TouristsController {

    private final DataBaseService service;

    @Autowired
    public TouristsController(DataBaseService service) {
        this.service = service;
    }

    @RequestMapping
    public String home(Model m) {
        m.addAttribute("allTourists", getAll());
        return "tourists";
    }

    @RequestMapping("/all")
    @ResponseBody
    public Iterable<Tourist> getAll() {
        return service.getTourists();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        service.deleteTourist(id);
        return "redirect:/tourists";
    }
}
