package ru.ssau.tourism.entities

import java.time.LocalDate
import javax.persistence.*

@Entity
class Season {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@ManyToOne
	@JoinColumn(name = "tour_id")
	lateinit var tour: Tour
	@Column
	lateinit var startDate: LocalDate
	@Column
	lateinit var finishDate: LocalDate
	@Column
	var isFinished: Boolean? = null
	@Column
	var countOfPlaces: Int? = null
}