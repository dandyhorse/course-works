package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ssau.realtor.controllers.utils.ActionTypeUtil;
import ru.ssau.realtor.entities.Trade;
import ru.ssau.realtor.services.DataBaseService;

@Controller
@RequestMapping("/trade")
public class TradeController {

	private final DataBaseService service;

	@Autowired
	public TradeController(DataBaseService service) {
		this.service = service;
	}

	@ResponseBody
	@GetMapping("/all")
	public Iterable<Trade> getAll() {
		return service.getAllTrades();
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		service.deleteTrade(id);
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("trades", getAll());
		return "trade";
	}

	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable Long id, Model model) {
		configureAttrForm(service.findTrade(id), model);
		model.addAttribute("action_type", ActionTypeUtil.EDIT_TYPE);
		return "forms/trade";
	}

	@GetMapping("/add/{id}")
	public String getAddForm(Model model) {
		Trade trade = new Trade();
		configureAttrForm(trade, model);
		model.addAttribute("action_type", ActionTypeUtil.ADD_TYPE);
		return "forms/trade";
	}

	private void configureAttrForm(Trade trade, Model model) {
		model.addAttribute("trade", trade);
		model.addAttribute("flats", service.getAllFlats());
		model.addAttribute("customers", service.getAllCustomers());
		model.addAttribute("sellers", service.getAllSellers());
	}

	@PostMapping("/edit")
	public String edit(@ModelAttribute Trade trade) {
		service.saveTrade(trade);
		return "redirect:/trade";
	}

	@PostMapping("/add")
	public String add(@ModelAttribute Trade trade) {
		service.saveTrade(trade);
		return "redirect:/trade";
	}
}
