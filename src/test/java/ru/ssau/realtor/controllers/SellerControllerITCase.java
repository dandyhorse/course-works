package ru.ssau.realtor.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.realtor.entities.Seller;
import ru.ssau.realtor.services.DataBaseService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SellerControllerITCase {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private DataBaseService service;

	@Test
	public void testGetAll() throws Exception {
		List<Seller> allSellers = service.getAllSellers();
		Seller[] actual = restTemplate.getForObject("/seller/all", Seller[].class);
		assertThat(actual).containsAll(allSellers);
	}
}