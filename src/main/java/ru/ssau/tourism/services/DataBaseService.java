package ru.ssau.tourism.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ssau.tourism.entities.Tour;
import ru.ssau.tourism.entities.Tourist;
import ru.ssau.tourism.repositories.TourRepository;
import ru.ssau.tourism.repositories.TouristRepository;

@Service
public class DataBaseService {

    private final TourRepository tourRepository;
    private final TouristRepository touristRepository;

    @Autowired
    public DataBaseService(TourRepository tourRepository, TouristRepository touristRepository) {
        this.tourRepository = tourRepository;
        this.touristRepository = touristRepository;
    }

    public Iterable<Tour> getTours() {
        return tourRepository.findAll();
    }

    public Iterable<Tourist> getTourists() {
        return touristRepository.findAll();
    }

    public void deleteTourist(Long id) {
        touristRepository.delete(id);
    }

    public void deleteTour(Long id) {
        tourRepository.delete(id);
    }

    public Tourist getTourist(Long id) {
        return touristRepository.findOne(id);
    }

    public Tourist saveTourist(Tourist t) {
        return touristRepository.save(t);
    }
}
