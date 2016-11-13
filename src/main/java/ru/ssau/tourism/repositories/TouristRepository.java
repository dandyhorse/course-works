package ru.ssau.tourism.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.Tourist;

public interface TouristRepository extends JpaRepository<Tourist, Long> {
}
