package ru.ssau.realtor.entities;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
@EqualsAndHashCode(of = "id")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	private Long id;
	@Column
	@NonNull @NotEmpty
	private String fio;
	@Column
	@NonNull @NotEmpty
	private String phoneNumber;
	@Column
	@NonNull @NotEmpty
	private String job;
	@Column
	@NonNull @NotEmpty
	private String post;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}
}
