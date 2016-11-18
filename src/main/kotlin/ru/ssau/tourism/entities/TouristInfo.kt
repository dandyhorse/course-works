package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class TouristInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@OneToOne
	@JoinColumn(name = "tourist_id")
	var tourist: Tourist? = null
	@Column
	var passport: String? = null
	@Column
	var city: String? = null
	@Column
	var country: String? = null
	@Column
	var phone_number: Int? = null
	@Column
	var index: Int? = null
}
