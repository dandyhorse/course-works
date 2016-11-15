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
	lateinit var voucher: Voucher
	@Column
	lateinit var payDay: LocalDate
	@Column
	var amount: Int? = null
}
