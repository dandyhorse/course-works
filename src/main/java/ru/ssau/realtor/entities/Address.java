package ru.ssau.realtor.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;

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
	@NonNull @Min(0)
	private int homeNumber;
	@Column
	@NonNull @Min(0)
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
