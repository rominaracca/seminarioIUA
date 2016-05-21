package com.magm.web.services.compras;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.magm.compras.model.Category;
import com.magm.web.services.Constants;
import com.magm.web.services.TestUtil;
import com.magm.web.spring.config.RootConfig;
import com.magm.web.spring.config.ServletInitializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RootConfig.class, ServletInitializer.class })
@WebAppConfiguration
public class CategoriesRSControllerTest {
	@Autowired
	private WebApplicationContext ctx;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void performCicle() throws Exception {
		Category c = new Category();
		c.setDescription("testCategory");

		// Add
		MvcResult mvcResult = mockMvc
				.perform(post(Constants.URL_CATEGORIES).contentType(MediaType.APPLICATION_JSON)
						.content(TestUtil.convertObjectToJsonBytes(c)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id", greaterThan(0)))
				.andExpect(jsonPath("$.description", is(c.getDescription())))
				.andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		int id = TestUtil.getIntValueFromJson(content, "id");

		// Update
		c.setDescription("Producto Test updated");
		mockMvc.perform(put(Constants.URL_CATEGORIES + "/" + id).contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(c)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.description", is(c.getDescription())));

		// Load
		mockMvc.perform(get(Constants.URL_CATEGORIES + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.description", is(c.getDescription())));

		// Delete
		mockMvc.perform(delete(Constants.URL_CATEGORIES + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		// Load Not Found
		mockMvc.perform(get(Constants.URL_CATEGORIES + "/" + id).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}
	
	@Test
	public void listCategories() throws Exception {
		mockMvc.perform(get(Constants.URL_CATEGORIES).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void loadCategoryNotFound() throws Exception {
		mockMvc.perform(get(Constants.URL_CATEGORIES + "/-999").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void deleteCategoryNotFound() throws Exception {
		mockMvc.perform(delete(Constants.URL_CATEGORIES + "/-999").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void updateCategoryNotFound() throws Exception {
		Category c = new Category();
		mockMvc.perform(put(Constants.URL_CATEGORIES + "/-999").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(c)).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()).andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Configuration
	@EnableWebMvc
	public static class TestConfiguration {

		@Bean
		public CategoriesRSController categoriesRSController() {
			return new CategoriesRSController();
		}
	}
}
