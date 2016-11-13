package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

@Controller
@RequestMapping("/tours")
public class TourController {

    private final DataBaseService service;

    @Autowired
    public TourController(DataBaseService service) {
        this.service = service;
    }

    @RequestMapping
    public String home(Model m) {
        m.addAttribute("allTours", getAll());
        return "tours";
    }

    @RequestMapping("/all")
    @ResponseBody
    public Iterable<Tour> getAll() {
        return service.getTours();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        service.deleteTour(id);
        return "redirect:/tours";
    }

    @GetMapping("/" + ActionUtil.editType)
    public String getPageForEdit(@RequestParam Long id) {
        return "redirect:/";
    }

    @PostMapping("/" + ActionUtil.editType)
    public String edit(@ModelAttribute Tour t) {
        return "redirect:/";
    }

    @GetMapping("/" + ActionUtil.addType)
    public String getPageForAdd(Model m) {
        return "redirect:/";
    }

    @PostMapping("/" + ActionUtil.addType)
    public String add(@ModelAttribute Tour t) {
        return "redirect:/";
    }
}
