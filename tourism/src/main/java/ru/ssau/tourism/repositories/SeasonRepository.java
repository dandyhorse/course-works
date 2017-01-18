package ru.ssau.tourism.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.Season;

public interface SeasonRepository extends CrudRepository<Season, Long> {}