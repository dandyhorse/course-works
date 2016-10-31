package ru.ssau.tourism.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.repositories.TourRepository;

@Service
public class DBService {

	@Autowired
	private TourRepository repository;

	public Iterable<Tour> getTours() {
		return repository.findAll();
	}

}
