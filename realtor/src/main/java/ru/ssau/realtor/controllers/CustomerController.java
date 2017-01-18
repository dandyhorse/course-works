package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.realtor.controllers.utils.ActionTypeUtil;
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

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteCustomer(id);
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("customers", getAll());
		return "customer";
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("customer", service.findCustomer(id));
		model.addAttribute("action_type", ActionTypeUtil.EDIT_TYPE);
		return "forms/customer";
	}

	@GetMapping("/add")
	public String getAddForm(Model m) {
		Customer customer = new Customer();
		m.addAttribute("customer", customer);
		m.addAttribute("action_type", ActionTypeUtil.ADD_TYPE);
		return "forms/customer";
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute Customer customer) {
		service.saveCustomer(customer);
		return "redirect:/customer";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute Customer customer) {
		service.saveCustomer(customer);
		return "redirect:/customer";
	}
}
