package ru.ssau.realtor.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ssau.realtor.entities.Seller;
import ru.ssau.realtor.repository.SellerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DataBaseServiceTest {

	@MockBean
	private SellerRepository sellerRepository;

	private DataBaseService service;

	@Before
	public void setUp() throws Exception {
		service = new DataBaseService(sellerRepository);
	}

	@Test
	public void getAllSellerTest() throws Exception {
		List<Seller> data = new ArrayList<>();
		Collections.addAll(data,
				Seller.of("Henry", "978"),
				Seller.of("Fuckoff", "88005553535"),
				Seller.of("Boris", "+1-754-9635-487"));
		when(sellerRepository.findAll()).thenReturn(data);
		Iterable<Seller> allSellers = service.getAllSeller();
		ArrayList<Seller> expected = new ArrayList<>(data);
		assertThat(allSellers, equalTo(expected));
	}
}