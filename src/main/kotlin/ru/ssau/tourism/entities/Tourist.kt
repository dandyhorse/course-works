package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Tourist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@Column
	lateinit var name: String
	@Column
	lateinit var surname: String
	@Column
	lateinit var fatherName: String
}