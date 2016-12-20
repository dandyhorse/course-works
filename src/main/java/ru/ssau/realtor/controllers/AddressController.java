package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.realtor.entities.Address;
import ru.ssau.realtor.services.DataBaseService;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {

	private final DataBaseService service;

	@Autowired
	public AddressController(DataBaseService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/all")
	public List<Address> getAll() {
		return service.getAllAddresses();
	}

	@GetMapping("/edit/{id}")
	public String getEditForm() {
		return "forms/address";
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("addresses", getAll());
		return "address";
	}
}
