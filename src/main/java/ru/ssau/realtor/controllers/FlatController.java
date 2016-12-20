package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.realtor.controllers.utils.ActionTypeUtil;
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

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteFlat(id);
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("flats", getAll());
		return "flat";
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("flat", service.findFlat(id));
		model.addAttribute("addresses", service.getAllAddresses());
		model.addAttribute("action_type", ActionTypeUtil.EDIT_TYPE);
		return "forms/flat";
	}

	@GetMapping("/add/{id}")
	public String getAddForm(Model model) {
		Flat flat = new Flat();
		model.addAttribute("flat", flat);
		model.addAttribute("addresses", service.getAllAddresses());
		model.addAttribute("action_type", ActionTypeUtil.ADD_TYPE);
		return "forms/flat";
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute Flat flat) {
		service.saveFlat(flat);
		return "redirect:/flat";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute Flat flat) {
		service.saveFlat(flat);
		return "redirect:/flat";
	}
}
