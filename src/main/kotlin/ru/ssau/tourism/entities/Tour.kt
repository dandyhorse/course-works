package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Tour {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@Column
	var title: String? = null
	@Column
	var price: Int? = null
	@Column
	var info: String? = null
}
