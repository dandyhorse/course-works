package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.realtor.entities.Customer;
import ru.ssau.realtor.services.DataBaseService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private final DataBaseService service;

	@Autowired
	public CustomerController(DataBaseService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/all")
	public Iterable<Customer> getAll() {
		return service.getAllCustomers();
	}

	@GetMapping("/edit/{id}")
	public String getEditForm() {
		return "forms/customer";
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("customers", getAll());
		return "customer";
	}
}
