package ru.ssau.realtor.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Trade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Long cost;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate tradeDate;
	@OneToOne
	@JoinColumn(name = "id_flat")
	private Flat flat;
	@OneToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;
	@OneToOne
	@JoinColumn(name = "id_seller")
	private Seller seller;

}
