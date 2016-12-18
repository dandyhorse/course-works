package ru.ssau.realtor.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
	private Date tradeDate;
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
