package ru.ssau.tourism.controllers

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@WebMvcTest(HomeController::class)
@ActiveProfiles("dev")
class HomeControllerTest {

	@Autowired
	private val mvc: MockMvc? = null

	@Value("\${app.author}")
	private val authorName: String? = null

	@Test
	fun getAuthorNameTest() {
		mvc!!.perform(get("/home").accept(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk).andExpect(model().attribute("author", authorName))
	}
}