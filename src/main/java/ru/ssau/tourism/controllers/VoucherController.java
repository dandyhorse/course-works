package ru.ssau.tourism.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tourism.entities.Voucher;
import ru.ssau.tourism.services.DataBaseService;
import ru.ssau.tourism.utils.ActionUtil;

@Controller
@RequestMapping("/vouchers")
public class VoucherController {

	private final DataBaseService service;

	@Autowired
	public VoucherController(DataBaseService service) {
		this.service = service;
	}

	// REST

	@GetMapping("/all")
	@ResponseBody
	public Iterable<Voucher> getAll() {
		return service.getAllVouchers();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteVoucher(id);
	}

	// MVC

	@GetMapping
	public String home(Model m) {
		m.addAttribute("all_vouchers", getAll());
		return "vouchers";
	}

	@GetMapping("/" + ActionUtil.EDIT_TYPE)
	public String getPageForEdit(@RequestParam Long id, Model m) {
		Voucher voucher = service.getVoucher(id);
		setAttributes(m, voucher);
		m.addAttribute("action_type", ActionUtil.EDIT_TYPE);
		return "forms/voucher";
	}

	@PostMapping("/" + ActionUtil.EDIT_TYPE)
	public String edit(@ModelAttribute Voucher voucher) {
		service.saveVoucher(voucher);
		return "redirect:/vouchers";
	}

	@GetMapping("/" + ActionUtil.ADD_TYPE)
	public String getPageForAdd(Model m) {
		Voucher voucher = new Voucher();
		setAttributes(m, voucher);
		m.addAttribute("action_type", ActionUtil.ADD_TYPE);
		return "forms/voucher";
	}

	@PostMapping("/" + ActionUtil.ADD_TYPE)
	public String add(@ModelAttribute Voucher voucher) {
		service.saveVoucher(voucher);
		return "redirect:/vouchers";
	}

	private void setAttributes(Model m, Voucher voucher) {
		m.addAttribute("voucher", voucher);
		m.addAttribute("all_tourists", service.getAllTourists());
		m.addAttribute("all_seasons", service.getAllSeasons());
	}
}
