package ru.ssau.tourism.entities

import javax.persistence.*

@Entity
class Voucher {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var id: Long? = null
	@ManyToOne
	@JoinColumn(name = "tourist_id")
	lateinit var tourist: Tourist
	@ManyToOne
	@JoinColumn(name = "season_id")
	lateinit var season: Season
}