package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Tourist {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@Column
	var name: String? = null
	@Column
	var surname: String? = null
	@Column
	var fatherName: String? = null
}