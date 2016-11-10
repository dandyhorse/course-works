package ru.ssau.tourism.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.ssau.tourism.entities.Voucher;

public interface VoucherRepository extends CrudRepository<Voucher, Long> {
}
