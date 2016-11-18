package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Payment;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

@Controller
@RequestMapping("/payments")
public class PaymentController {

	private final DataBaseService service;

	@Autowired
	public PaymentController(DataBaseService service) {
		this.service = service;
	}

	// REST

	@GetMapping("/all")
	@ResponseBody
	public Iterable<Payment> getAll() {
		return service.getAllPayments();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deletePayment(id);
	}

	// MVC

	@GetMapping
	public String home(Model m) {
		m.addAttribute("all_payments", getAll());
		return "payments";
	}

	@GetMapping("/" + ActionUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		Payment payment = service.getPayment(id);
		m.addAttribute("payment", payment);
		m.addAttribute("all_vouchers", service.getAllVouchers());
		m.addAttribute("action_type", ActionUtil.EDIT_TYPE);
		return "forms/payment";
	}

	@PostMapping("/" + ActionUtil.EDIT_TYPE)
	public String edit(@ModelAttribute Payment payment) {
		service.savePayment(payment);
		return "redirect:/payments";
	}

	@GetMapping("/" + ActionUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		Payment payment = new Payment();
		m.addAttribute("payment", payment);
		m.addAttribute("all_vouchers", service.getAllVouchers());
		m.addAttribute("action_type", ActionUtil.ADD_TYPE);
		return "forms/payment";
	}

	@PostMapping("/" + ActionUtil.ADD_TYPE)
	public String add(@ModelAttribute Payment payment) {
		service.savePayment(payment);
		return "redirect:/payments";
	}
}
