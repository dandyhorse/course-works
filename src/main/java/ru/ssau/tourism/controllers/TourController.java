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

	// REST

	@RequestMapping("/all")
	@ResponseBody
	public Iterable<Tour> getAll() {
		return service.getTours();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteTour(id);
	}

	// MVC
	
	@RequestMapping
	public String home(Model m) {
		m.addAttribute("allTours", getAll());
		return "tours";
	}

	@GetMapping("/" + ActionUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		Tour tour = service.getTour(id);
		m.addAttribute("tour", tour);
		m.addAttribute("action_type", ActionUtil.EDIT_TYPE);
		return "forms/tour";
	}

	@PostMapping("/" + ActionUtil.EDIT_TYPE)
	public String edit(@ModelAttribute("tour") Tour tour) {
		service.saveTour(tour);
		return "redirect:/tours";
	}

	@GetMapping("/" + ActionUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		Tour tour = new Tour();
		m.addAttribute("tour", tour);
		m.addAttribute("action_type", ActionUtil.ADD_TYPE);
		return "forms/tour";
	}

	@PostMapping("/" + ActionUtil.ADD_TYPE)
	public String add(@ModelAttribute Tour t) {
		return "redirect:/";
	}
}
