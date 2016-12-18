package ru.ssau.realtor.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
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

}
