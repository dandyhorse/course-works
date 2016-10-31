package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.services.DBService;

@Controller
@RequestMapping("/")
public class TourController {

	@Autowired
	private DBService service;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Tour> getAll() {
		return service.getTours();
	}

}
