package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Tour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@Column
	lateinit var title: String
	@Column
	var price: Int? = null
	@Column
	lateinit var info: String
}
