package ru.ssau.tourism.entities

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.*

@Entity
class Season constructor(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null,
		@ManyToOne
		@JoinColumn(name = "tour_id")
		var tour: Tour? = null,
		@Column
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		var startDate: LocalDate? = null,
		@Column
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		var finishDate: LocalDate? = null,
		@Column
		var finished: Boolean? = null,
		@Column
		var countOfPlaces: Int? = null

) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		other as Season
		if (id != other.id) return false
		if (startDate != other.startDate) return false
		if (finishDate != other.finishDate) return false
		return true
	}

	override fun hashCode(): Int {
		var result = id?.hashCode() ?: 0
		result = 31 * result + (startDate?.hashCode() ?: 0)
		result = 31 * result + (finishDate?.hashCode() ?: 0)
		return result
	}
}