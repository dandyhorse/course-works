package ru.ssau.realtor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.realtor.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {}