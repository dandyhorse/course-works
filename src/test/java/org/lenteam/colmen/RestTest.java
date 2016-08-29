package org.lenteam.colmen;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lenteam.colmen.controllers.DefaultRestController;
import org.lenteam.colmen.entites.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Anton_Solovev
 * @since 8/21/2016
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DefaultRestController.class)
public class RestTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DefaultRestController controller;

    @Test
    @WithMockUser
    public void getEntityTest() throws Exception {
        PersonEntity anton = new PersonEntity("Anton");
        when(controller.getDefaultPerson(any())).thenReturn(anton);
        mvc.perform(get("/default/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name", is("Anton")));
    }

}