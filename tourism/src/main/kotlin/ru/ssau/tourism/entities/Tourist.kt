package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Tourist constructor(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null,
		@Column
		var name: String? = null,
		@Column
		var surname: String? = null,
		@Column
		var fatherName: String? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		other as Tourist
		if (id != other.id) return false
		return true
	}

	override fun hashCode(): Int {
		return id?.hashCode() ?: 0
	}
}