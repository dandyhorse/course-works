package ru.ssau.realtor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.realtor.entities.Flat;

@Repository
public interface FlatRepository extends CrudRepository<Flat, Long>{}
