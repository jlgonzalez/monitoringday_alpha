package com.adidas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.isNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * Out-of-container test for the Spring Boot app.
 * Ensures that we can retrieve team information.
 * Verifies the entire stack, from DispatcherServlet to DB, without running a server.
 * 
 * @author ken krueger
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ApplicationTests {

	@Autowired WebApplicationContext spring;
	MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(spring).build();
	}
	
	@Test
	public void articlesRetrieve() throws Exception {

		//	Ensure that everything works when we do a GET for all teams:
		mockMvc.perform(get("/articles"))
		.andExpect(status().isOk())
		//.andExpect(jsonPath("$[0].id").value(1))
		//.andExpect(jsonPath("$[0].modelName").value("Stan Smith"))
		//.andExpect(jsonPath("$[0].image").value("001.jpg"))
		//.andExpect(jsonPath("$[0].price").value("120"))
		;
	}



	@Test
	public void articleRetrieve() throws Exception {

		//	Ensure that everything works when we do a GET for a specific player.
		mockMvc.perform(get("/articles/1"))
		.andExpect(status().isOk())
		;
	}

}
