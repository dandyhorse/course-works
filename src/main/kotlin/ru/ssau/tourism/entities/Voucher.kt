package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@ManyToOne
	@JoinColumn(name = "tourist_id")
	var tourist: Tourist? = null
	@ManyToOne
	@JoinColumn(name = "season_id")
	var season: Season? = null
}