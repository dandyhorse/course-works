package ru.ssau.tourism.controllers

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import ru.ssau.tourism.entities.*
import ru.ssau.tourism.services.DataBaseService


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
class RestTest {

	@Autowired
	private var restTemplate: TestRestTemplate? = null

	@Autowired
	private var service: DataBaseService? = null

	@Test
	fun getAllToursTest() {
		val body = restTemplate!!.getForObject("/tours/all", Array<Tour>::class.java)
		assertThat(body).containsAll(service!!.allTours)
	}

	@Test
	fun getAllTouristsTest() {
		val body = restTemplate!!.getForObject("/tourists/all", Array<Tourist>::class.java)
		assertThat(body).containsAll(service!!.allTourists)
	}

	@Test
	fun getAllSeasonsTest() {
		val body = restTemplate!!.getForObject("/seasons/all", Array<Season>::class.java)
		assertThat(body).containsAll(service!!.allSeasons)
	}

	@Test
	fun getAllVouchersTest() {
		val body = restTemplate!!.getForObject("/vouchers/all", Array<Voucher>::class.java)
		assertThat(body).containsAll(service!!.allVouchers)
	}

	@Test
	fun getAllTouristsInfoTest() {
		val body = restTemplate!!.getForObject("/tourists-info/all", Array<TouristInfo>::class.java)
		assertThat(body).containsAll(service!!.allTouristInfo)
	}

	@Test
	fun getAllPaymentsTest() {
		val body = restTemplate!!.getForObject("/payments/all", Array<Payment>::class.java)
		assertThat(body).containsAll(service!!.allPayments)
	}
}
