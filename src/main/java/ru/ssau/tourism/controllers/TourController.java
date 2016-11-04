package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.services.DBService;

@Controller
public class TourController {

    private final DBService service;

    @Autowired
    public TourController(DBService service) {
        this.service = service;
    }

    @RequestMapping("/all")
    @ResponseBody
    public Iterable<Tour> get() {
        return service.getTours();
    }

}
