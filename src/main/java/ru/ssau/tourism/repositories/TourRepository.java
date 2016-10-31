package ru.ssau.tourism.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.Tour;

public interface TourRepository extends CrudRepository<Tour, Long> {
}
