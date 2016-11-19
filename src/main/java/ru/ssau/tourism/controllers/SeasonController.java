package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Season;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

@Controller
@RequestMapping("/seasons")
public class SeasonController {

	private final DataBaseService service;

	@Autowired
	public SeasonController(DataBaseService service) {
		this.service = service;
	}

	// REST

	@GetMapping("/all")
	@ResponseBody
	public Iterable<Season> getAll() {
		return service.getAllSeasons();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteSeason(id);
	}

	// MVC

	@GetMapping
	public String home(Model m) {
		m.addAttribute("all_seasons", getAll());
		return "seasons";
	}

	@GetMapping("/" + ActionUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		Season season = service.getSeason(id);
		m.addAttribute("season", season);
		m.addAttribute("all_tours", service.getAllTours());
		m.addAttribute("action_type", ActionUtil.EDIT_TYPE);
		return "forms/season";
	}

	@PostMapping("/" + ActionUtil.EDIT_TYPE)
	public String edit(@ModelAttribute Season season) {
		service.saveSeason(season);
		return "redirect:/seasons";
	}

	@GetMapping("/" + ActionUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		Season season = new Season();
		m.addAttribute("season", season);
		m.addAttribute("all_tours", service.getAllTours());
		m.addAttribute("action_type", ActionUtil.ADD_TYPE);
		return "forms/season";
	}

	@PostMapping("/" + ActionUtil.ADD_TYPE)
	public String add(@ModelAttribute Season season) {
		service.saveSeason(season);
		return "redirect:/seasons";
	}
}
