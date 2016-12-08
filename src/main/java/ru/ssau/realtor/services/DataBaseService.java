package ru.ssau.realtor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.realtor.entities.Seller;
import ru.ssau.realtor.repository.SellerRepository;

@Service
public class DataBaseService {

	private SellerRepository sellerRepository;

	@Autowired
	public DataBaseService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public Iterable<Seller> getAllSeller() {
		return sellerRepository.findAll();
	}
}
