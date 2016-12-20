package ru.ssau.realtor.entities;

import lombok.*;

import javax.persistence.*;

@Entity
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

	public Long getId() {
		return id;
	}

	public String getRegion() {
		return region;
	}

	public String getStreet() {
		return street;
	}

	public int getHomeNumber() {
		return homeNumber;
	}

	public int getFlatNumber() {
		return flatNumber;
	}
}
