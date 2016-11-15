package ru.ssau.tourism.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {}