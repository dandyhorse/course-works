package ru.ssau.realtor.entities;

import javax.persistence.*;

@Entity
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String FIO;
	@Column
	private String phoneNumber;

	public Seller() {}

	public Seller(String FIO, String phoneNumber) {
		this.FIO = FIO;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFIO() {
		return FIO;
	}

	public void setFIO(String FIO) {
		this.FIO = FIO;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static Seller of(String fio, String number) {
		return new Seller(fio, number);
	}
}
