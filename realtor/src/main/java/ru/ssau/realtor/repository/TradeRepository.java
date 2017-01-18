package ru.ssau.realtor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.realtor.entities.Trade;

@Repository
public interface TradeRepository extends CrudRepository<Trade, Long> {}
