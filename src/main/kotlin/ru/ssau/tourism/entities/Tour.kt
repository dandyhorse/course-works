package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Tour constructor(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null,
		@Column
		var title: String? = null,
		@Column
		var price: Int? = null,
		@Column
		var info: String? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		other as Tour
		if (id != other.id) return false
		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}
}
