package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.realtor.entities.Flat;
import ru.ssau.realtor.services.DataBaseService;

import java.util.List;

@Controller
@RequestMapping("/flat")
public class FlatController {

	private final DataBaseService service;

	@Autowired
	public FlatController(DataBaseService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/all")
	public List<Flat> getAll() {
		return service.getAllFlats();
	}

	@GetMapping("/edit/{id}")
	public String getEditForm() {
		return "forms/flat";
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("customers", getAll());
		return "flat";
	}
}
