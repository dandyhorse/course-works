package ru.ssau.tourism.entities

import java.time.LocalDate
import javax.persistence.*

@Entity
class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@ManyToOne
	@JoinColumn(name = "voucher_id")
	var voucher: Voucher? = null
	@Column
	var payDay: LocalDate? = null
	@Column
	var amount: Int? = null
}
