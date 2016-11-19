package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.TouristInfo;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

@Controller
@RequestMapping("/tourists-info")
public class TouristInfoController {

	private final DataBaseService service;

	@Autowired
	public TouristInfoController(DataBaseService service) {
		this.service = service;
	}

	// REST

	@GetMapping("/all")
	@ResponseBody
	public Iterable<TouristInfo> getAll() {
		return service.getAllTouristInfo();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteTouristInfo(id);
	}

	// MVC

	@GetMapping
	public String home(Model m) {
		m.addAttribute("all_tourists_info", getAll());
		return "tourists-info";
	}

	@GetMapping("/" + ActionUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		TouristInfo touristInfo = service.getTouristInfo(id);
		m.addAttribute("tourists_info", touristInfo);
		m.addAttribute("all_tourists", service.getAllTourists());
		m.addAttribute("action_type", ActionUtil.EDIT_TYPE);
		return "forms/tourist-info";
	}

	@PostMapping("/" + ActionUtil.EDIT_TYPE)
	public String edit(@ModelAttribute TouristInfo touristInfo) {
		service.saveTouristInfo(touristInfo);
		return "redirect:/tourists-info";
	}

	@GetMapping("/" + ActionUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		TouristInfo touristInfo = new TouristInfo();
		m.addAttribute("tourists_info", touristInfo);
		m.addAttribute("all_tourists", service.getAllTourists());
		m.addAttribute("action_type", ActionUtil.ADD_TYPE);
		return "forms/tourist-info";
	}

	@PostMapping("/" + ActionUtil.ADD_TYPE)
	public String add(@ModelAttribute TouristInfo touristInfo) {
		service.saveTouristInfo(touristInfo);
		return "redirect:/tourists-info";
	}
}
