package ru.ssau.tourism.entities

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.persistence.*

@Entity
class Payment constructor(
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long? = null,
		@ManyToOne
		@JoinColumn(name = "voucher_id")
		var voucher: Voucher? = null,
		@Column
		@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
		var payDay: LocalDate? = null,
		@Column
		var amount: Int? = null
) {
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other?.javaClass != javaClass) return false
		other as Payment
		if (id != other.id) return false
		if (payDay != other.payDay) return false
		return true
	}

	override fun hashCode(): Int {
		var result = id?.hashCode() ?: 0
		result = 31 * result + (payDay?.hashCode() ?: 0)
		return result
	}
}
