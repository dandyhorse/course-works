package ru.ssau.tourism.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.TouristInfo;

public interface TouristInfoRepository extends CrudRepository<TouristInfo, Long> {
}
