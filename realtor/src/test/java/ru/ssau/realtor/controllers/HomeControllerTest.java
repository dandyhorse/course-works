package ru.ssau.realtor.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
@ActiveProfiles("test")
public class HomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testHomePage() throws Exception {
        mvc.perform(get("/home").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(model().attribute("name", HomeController.NAME));
    }

}