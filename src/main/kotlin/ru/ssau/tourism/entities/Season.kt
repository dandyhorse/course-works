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
	var tour: Tour? = null
	@Column
	 var startDate: LocalDate? = null
	@Column
	var finishDate: LocalDate? = null
	@Column
	var isFinished: Boolean? = null
	@Column
	var countOfPlaces: Int? = null
}