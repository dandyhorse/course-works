package ru.ssau.realtor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.realtor.entities.Seller;
import ru.ssau.realtor.repository.SellerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {

	private SellerRepository sellerRepository;

	@Autowired
	public DataBaseService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public List<Seller> getAllSellers() {
		Iterable<Seller> all = sellerRepository.findAll();
		return toList(all);
	}

	private <T> List<T> toList(Iterable<T> all) {
		List<T> items = new ArrayList<>();
		all.forEach(items::add);
		return items;
	}
}
