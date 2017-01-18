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
		return toList(sellerRepository.findAll());
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

	public List<Flat> getAllFlats() {
		return toList(flatRepository.findAll());
	}

	public List<Address> getAllAddresses() {
		return toList(addressRepository.findAll());
	}

	public Seller findSeller(Long id) {
		return sellerRepository.findOne(id);
	}

	public Flat findFlat(Long id) {
		return flatRepository.findOne(id);
	}

	public Customer findCustomer(Long id) {
		return customerRepository.findOne(id);
	}

	public Trade findTrade(Long id) {
		return tradeRepository.findOne(id);
	}

	public Address findAddress(Long id) {
		return addressRepository.findOne(id);
	}

	public void deleteSeller(Long id) {
		sellerRepository.delete(id);
	}

	public void deleteAddress(Long id) {
		addressRepository.delete(id);
	}

	public void deleteFlat(Long id) {
		flatRepository.delete(id);
	}

	public void deleteTrade(Long id) {
		tradeRepository.delete(id);
	}

	public void deleteCustomer(Long id) {
		customerRepository.delete(id);
	}

	public void saveSeller(Seller seller) {
		sellerRepository.save(seller);
	}

	public void saveTrade(Trade trade) {
		tradeRepository.save(trade);
	}

	public void saveFlat(Flat flat) {
		flatRepository.save(flat);
	}

	public void saveAddress(Address address) {
		addressRepository.save(address);
	}

	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
}
