package ru.ssau.realtor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@GetMapping("/edit/{id}")
	public String getEditForm() {
		return "seller";
	}

	@GetMapping
	public String getViewPage(Model model) {
		model.addAttribute("trades", getAll());
		return "trade";
	}
}
