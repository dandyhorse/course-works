package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Tourist;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

@Controller
@RequestMapping("/tourists")
public class TouristsController {

	private final DataBaseService service;

	@Autowired
	public TouristsController(DataBaseService service) {
		this.service = service;
	}

	// REST

	@GetMapping("/all")
	@ResponseBody
	public Iterable<Tourist> getAll() {
		return service.getAllTourists();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteTourist(id);
	}

	// MVC

	@GetMapping()
	public String home(Model m) {
		m.addAttribute("all_tourists", getAll());
		return "tourists";
	}

	@GetMapping("/" + ActionUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		Tourist tourist = service.getTourist(id);
		m.addAttribute("tourist", tourist);
		m.addAttribute("action_type", ActionUtil.EDIT_TYPE);
		return "forms/tourist";
	}

	@PostMapping("/" + ActionUtil.EDIT_TYPE)
	public String edit(@ModelAttribute Tourist tourist) {
		service.saveTourist(tourist);
		return "redirect:/tourists";
	}

	@GetMapping("/" + ActionUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		Tourist tourist = new Tourist();
		m.addAttribute("tourist", tourist);
		m.addAttribute("action_type", ActionUtil.ADD_TYPE);
		return "forms/tourist";
	}

	@PostMapping("/" + ActionUtil.ADD_TYPE)
	public String add(@ModelAttribute Tourist tourist) {
		service.saveTourist(tourist);
		return "redirect:/tourists";
	}
}
