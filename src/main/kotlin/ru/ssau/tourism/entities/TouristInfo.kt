package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class TouristInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@OneToOne
	@JoinColumn(name = "tourist_id")
	lateinit var tourist: Tourist
	@Column
	lateinit var passport: String
	@Column
	lateinit var city: String
	@Column
	lateinit var country: String
	@Column
	var phone_number: Int? = null
	@Column
	var index: Int? = null
}
