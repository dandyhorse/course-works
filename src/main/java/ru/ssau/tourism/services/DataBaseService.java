package ru.ssau.tourism.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tourism.entities.*;
import ru.ssau.tourism.repositories.*;

@Service
public class DataBaseService {

	private final TourRepository tourRepository;
	private final TouristRepository touristRepository;
	private final TouristInfoRepository touristInfoRepository;
	private final PaymentRepository paymentRepository;
	private final SeasonRepository seasonRepository;
	private final VoucherRepository voucherRepository;

	@Autowired
	public DataBaseService(TourRepository tourRepository,
						   TouristRepository touristRepository,
						   TouristInfoRepository touristInfoRepository,
						   PaymentRepository paymentRepository,
						   SeasonRepository seasonRepository,
						   VoucherRepository voucherRepository) {
		this.tourRepository = tourRepository;
		this.touristRepository = touristRepository;
		this.touristInfoRepository = touristInfoRepository;
		this.paymentRepository = paymentRepository;
		this.seasonRepository = seasonRepository;
		this.voucherRepository = voucherRepository;
	}

	// tours

	public Iterable<Tour> getAllTours() {
		return tourRepository.findAll();
	}

	public Tour getTour(Long id) {
		return tourRepository.findOne(id);
	}

	public void saveTour(Tour tour) {
		tourRepository.save(tour);
	}

	public void deleteTour(Long id) {
		tourRepository.delete(id);
	}

	// tourists

	public Tourist getTourist(Long id) {
		return touristRepository.findOne(id);
	}

	public Iterable<Tourist> getAllTourists() {
		return touristRepository.findAll();
	}

	public Tourist saveTourist(Tourist t) {
		return touristRepository.save(t);
	}

	public void deleteTourist(Long id) {
		touristRepository.delete(id);
	}

	// tourists-infos

	public Iterable<TouristInfo> getAllTouristInfos() {
		return touristInfoRepository.findAll();
	}

	public TouristInfo getTouristInfo(Long id) {
		return touristInfoRepository.findOne(id);
	}

	public TouristInfo saveTouristInfo(TouristInfo touristInfo) {
		return touristInfoRepository.save(touristInfo);
	}

	public void deleteTouristInfo(Long id) {
		touristInfoRepository.delete(id);
	}

	// payments

	public Iterable<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	public Payment getPayment(Long id) {
		return paymentRepository.findOne(id);
	}

	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	public void deletePayment(Long id) {
		paymentRepository.delete(id);
	}

	// seasons

	public Iterable<Season> getAllSeasons() {
		return seasonRepository.findAll();
	}

	public Season getSeason(Long id) {
		return seasonRepository.findOne(id);
	}

	public Season saveSeason(Season season) {
		return seasonRepository.save(season);
	}

	public void deleteSeason(Long id) {
		seasonRepository.delete(id);
	}

	// vouchers

	public Iterable<Voucher> getAllVouchers() {
		return voucherRepository.findAll();
	}

	public Voucher getVoucher(Long id) {
		return voucherRepository.findOne(id);
	}

	public Voucher saveVoucher(Voucher voucher) {
		return voucherRepository.save(voucher);
	}

	public void deleteVoucher(Long id) {
		voucherRepository.delete(id);
	}
}
