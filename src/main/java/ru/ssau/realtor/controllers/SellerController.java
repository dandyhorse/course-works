package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.realtor.controllers.utils.ActionTypeUtil;
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

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteSeller(id);
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("sellers", getAll());
		return "seller";
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("seller", service.findSeller(id));
		model.addAttribute("action_type", ActionTypeUtil.EDIT_TYPE);
		return "forms/seller";
	}

	@GetMapping("/add/{id}")
	public String getAddForm(Model m) {
		Seller seller = new Seller();
		m.addAttribute("seller", seller);
		m.addAttribute("action_type", ActionTypeUtil.ADD_TYPE);
		return "forms/seller";
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute Seller seller) {
		service.saveSeller(seller);
		return "redirect:/seller";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute Seller seller) {
		service.saveSeller(seller);
		return "redirect:/seller";
	}
}
