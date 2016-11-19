package ru.ssau.tourism.entities

import org.springframework.format.annotation.DateTimeFormat
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
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	var startDate: LocalDate? = null
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	var finishDate: LocalDate? = null
	@Column
	var finished: Boolean? = false
	@Column
	var countOfPlaces: Int? = null
}