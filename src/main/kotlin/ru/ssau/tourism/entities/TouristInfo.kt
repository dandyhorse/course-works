package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class TouristInfo constructor(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null,
		@OneToOne
		@JoinColumn(name = "tourist_id")
		var tourist: Tourist? = null,
		@Column
		var passport: String? = null,
		@Column
		var city: String? = null,
		@Column
		var country: String? = null,
		@Column
		var phone_number: Int? = null,
		@Column
		var index: Int? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		other as TouristInfo
		if (id != other.id) return false
		if (passport != other.passport) return false
		return true
	}

	override fun hashCode(): Int {
		var result = id?.hashCode() ?: 0
		result = 31 * result + (passport?.hashCode() ?: 0)
		return result
	}
}
