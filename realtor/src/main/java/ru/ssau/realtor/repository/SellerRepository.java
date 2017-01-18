package ru.ssau.realtor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.realtor.entities.Seller;

@Repository
public interface SellerRepository extends CrudRepository<Seller, Long> {}