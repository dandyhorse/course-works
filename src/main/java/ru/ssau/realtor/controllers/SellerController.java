package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.ssau.realtor.entities.Seller;
import ru.ssau.realtor.services.DataBaseService;

@Controller
@RequestMapping("/seller")
public class SellerController {

	private final DataBaseService service;

	@Autowired
	public SellerController(DataBaseService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/all")
	public Iterable<Seller> getAll() {
		return service.getAllSellers();
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("seller", service.findSeller(id));
		return "seller";
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("sellers", getAll());
		return "seller";
	}
}
