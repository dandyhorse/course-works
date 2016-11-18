package ru.ssau.tourism.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.entities.Tourist;
import ru.ssau.tourism.entities.TouristInfo;
import ru.ssau.tourism.repositories.TourRepository;
import ru.ssau.tourism.repositories.TouristInfoRepository;
import ru.ssau.tourism.repositories.TouristRepository;


@Service
public class DataBaseService {

	private final TourRepository tourRepository;
	private final TouristRepository touristRepository;
	private final TouristInfoRepository touristInfoRepository;

	@Autowired
	public DataBaseService(TourRepository tourRepository, TouristRepository touristRepository, TouristInfoRepository touristInfoRepository) {
		this.tourRepository = tourRepository;
		this.touristRepository = touristRepository;
		this.touristInfoRepository = touristInfoRepository;
	}

	// tours

	public Iterable<Tour> getTours() {
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

}
