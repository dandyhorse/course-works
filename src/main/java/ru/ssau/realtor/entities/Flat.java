package ru.ssau.realtor.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id")
public class Flat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_address")
	private Address address;
	@Column
	@NonNull @NotEmpty
	private String typeHome;
	@Column
	@NonNull
	private int commonSpace;
	@Column
	@NonNull
	private int residentSpace;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTypeHome() {
		return typeHome;
	}

	public void setTypeHome(String typeHome) {
		this.typeHome = typeHome;
	}

	public int getCommonSpace() {
		return commonSpace;
	}

	public void setCommonSpace(int commonSpace) {
		this.commonSpace = commonSpace;
	}

	public int getResidentSpace() {
		return residentSpace;
	}

	public void setResidentSpace(int residentSpace) {
		this.residentSpace = residentSpace;
	}
}
