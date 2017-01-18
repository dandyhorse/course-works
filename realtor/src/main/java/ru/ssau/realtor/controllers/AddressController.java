package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.realtor.controllers.utils.ActionTypeUtil;
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

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteAddress(id);
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("addresses", getAll());
		return "address";
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("address", service.findAddress(id));
		model.addAttribute("action_type", ActionTypeUtil.EDIT_TYPE);
		return "forms/address";
	}

	@GetMapping("/add")
	public String getAddForm(Model m) {
		Address address = new Address();
		m.addAttribute("address", address);
		m.addAttribute("action_type", ActionTypeUtil.ADD_TYPE);
		return "forms/address";
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute Address address) {
		service.saveAddress(address);
		return "redirect:/address";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute Address address) {
		service.saveAddress(address);
		return "redirect:/address";
	}
}
