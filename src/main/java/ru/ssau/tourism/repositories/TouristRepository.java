package ru.ssau.tourism.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.Tourist;

public interface TouristRepository extends CrudRepository<Tourist, Long> {
}
