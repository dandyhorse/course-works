package ru.ssau.realtor.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String fio;
	@Column
	private String phoneNumber;
	@Column
	private String job;
	@Column
	private String post;

}
