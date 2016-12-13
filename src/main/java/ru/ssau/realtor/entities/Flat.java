package ru.ssau.realtor.entities;

import javax.persistence.*;

@Entity
public class Flat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "id_address")
	private Address address;
	@Column
	private String typeHome;
	@Column
	private int commonSpace;
	@Column
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
