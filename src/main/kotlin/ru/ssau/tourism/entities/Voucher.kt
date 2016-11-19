package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Voucher constructor(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null,
		@ManyToOne
		@JoinColumn(name = "tourist_id")
		var tourist: Tourist? = null,
		@ManyToOne
		@JoinColumn(name = "season_id")
		var season: Season? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		other as Voucher
		if (id != other.id) return false
		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}
}