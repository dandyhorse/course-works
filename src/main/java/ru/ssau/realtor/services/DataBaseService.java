package ru.ssau.realtor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.realtor.entities.*;
import ru.ssau.realtor.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {

	private SellerRepository sellerRepository;
	private CustomerRepository customerRepository;
	private FlatRepository flatRepository;
	private TradeRepository tradeRepository;
	private AddressRepository addressRepository;

	@Autowired
	public DataBaseService(
			SellerRepository sellerRepository,
			CustomerRepository customerRepository,
			FlatRepository flatRepository,
			TradeRepository tradeRepository,
			AddressRepository addressRepository) {
		this.sellerRepository = sellerRepository;
		this.customerRepository = customerRepository;
		this.flatRepository = flatRepository;
		this.tradeRepository = tradeRepository;
		this.addressRepository = addressRepository;
	}

	DataBaseService(SellerRepository sellerRepository) {
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

	public Iterable<Trade> getAllTrades() {
		return tradeRepository.findAll();
	}

	public Iterable<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Iterable<Flat> getAllFlats() {
		return flatRepository.findAll();
	}

	public Iterable<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	public Seller findSeller(Long id) {
		return sellerRepository.findOne(id);
	}
}
