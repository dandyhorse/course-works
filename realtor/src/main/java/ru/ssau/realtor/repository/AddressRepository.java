package ru.ssau.realtor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.realtor.entities.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {}