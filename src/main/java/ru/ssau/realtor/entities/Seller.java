package ru.ssau.realtor.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	@NonNull @NotEmpty
	private String fio;
	@Column
	@NonNull @NotEmpty
	private String phoneNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public static Seller of(String fio, String number) {
		Seller seller = new Seller();
		seller.setFio(fio);
		seller.setPhoneNumber(number);
		return seller;
	}
}
