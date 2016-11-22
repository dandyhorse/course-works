package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionTypeUtil;

@Controller
@RequestMapping("/tours")
public class TourController {

	private final DataBaseService service;

	@Autowired
	public TourController(DataBaseService service) {
		this.service = service;
	}

	// REST

	@RequestMapping("/all")
	@ResponseBody
	public Iterable<Tour> getAll() {
		return service.getAllTours();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteTour(id);
	}

	// MVC

	@GetMapping
	public String home(Model m) {
		m.addAttribute("all_tours", getAll());
		return "tours";
	}

	@GetMapping("/" + ActionTypeUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		Tour tour = service.getTour(id);
		m.addAttribute("tour", tour);
		m.addAttribute("action_type", ActionTypeUtil.EDIT_TYPE);
		return "forms/tour";
	}

	@PostMapping("/" + ActionTypeUtil.EDIT_TYPE)
	public String edit(@ModelAttribute Tour tour) {
		service.saveTour(tour);
		return "redirect:/tours";
	}

	@GetMapping("/" + ActionTypeUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		Tour tour = new Tour();
		m.addAttribute("tour", tour);
		m.addAttribute("action_type", ActionTypeUtil.ADD_TYPE);
		return "forms/tour";
	}

	@PostMapping("/" + ActionTypeUtil.ADD_TYPE)
	public String add(@ModelAttribute Tour tour) {
		service.saveTour(tour);
		return "redirect:/tours";
	}
}
