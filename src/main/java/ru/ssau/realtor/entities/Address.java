package ru.ssau.realtor.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	@NonNull @NotEmpty
	private String region;
	@Column
	@NonNull @NotEmpty
	private String street;
	@Column
	@NonNull
	private int homeNumber;
	@Column
	@NonNull
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
