package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Tourist;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

import javax.validation.Valid;

@Controller
@RequestMapping("/tourists")
public class TouristsController {

    private final DataBaseService service;

    @Autowired
    public TouristsController(DataBaseService service) {
        this.service = service;
    }

    @GetMapping()
    public String home(Model m) {
        m.addAttribute("allTourists", getAll());
        return "tourists";
    }

    @GetMapping("/all")
    @ResponseBody
    public Iterable<Tourist> getAll() {
        return service.getTourists();
    }

    @PostMapping(value = "/delete",
            consumes = "application/json; charset=utf-8")
    public String delete(@RequestBody Long id) {
        service.deleteTourist(id);
        return "redirect:/tourists";
    }

    @GetMapping("/" + ActionUtil.editType)
    public String getPageForEdit(@RequestParam Long id, Model m) {
        Tourist tourist = service.getTourist(id);
        m.addAttribute("tourist", tourist);
        m.addAttribute("action_type", ActionUtil.editType);
        return "forms/tourist";
    }

    @PostMapping("/" + ActionUtil.editType)
    public String edit(@Valid @ModelAttribute Tourist tourist) {
        service.saveTourist(tourist);
        return "redirect:/tourists";
    }

    @GetMapping("/" + ActionUtil.addType)
    public String getPageForAdd(Model m) {
        Tourist tourist = new Tourist();
        m.addAttribute("tourist", tourist);
        m.addAttribute("action_type", ActionUtil.addType);
        return "forms/tourist";
    }

    @PostMapping("/" + ActionUtil.addType)
    public String add(@ModelAttribute Tourist tourist) {
        return "";
    }
}
