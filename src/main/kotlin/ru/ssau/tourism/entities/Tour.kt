package ru.ssau.tourism.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Tour(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long,
		var title: String,
		var price: Int,
		var info: String)