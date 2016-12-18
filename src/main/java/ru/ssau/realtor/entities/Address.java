package ru.ssau.realtor.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String region;
	@Column
	private String street;
	@Column
	private int homeNumber;
	@Column
	private int flatNumber;

}
