package com.levi9.taster.rest.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.codiform.moo.curry.Translate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.levi9.taster.core.api.CategoryService;
import com.levi9.taster.rest.api.resource.CategoryResource;

/**
 * CategoryController Integration test.
 *
 * @author m.zorboski
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jpa")
@ContextConfiguration("classpath:com/levi9/taster/rest/api/ctx/application-config.xml")
@WebAppConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:testData.xml")
public class CategoryControllerIT {

	@Resource
	private WebApplicationContext webApplicationContext;
	
	@Inject
	private CategoryService categoryService;

	private MockMvc mockMvc;
	
	/**
	 * Sets the tests up.
	 */
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testList() throws Exception {
		mockMvc.perform(get("/api/categories")
				.param("number", "0")
		 		.param("size", "10"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"))))
				// Assert response values
				.andExpect(jsonPath("$", Matchers.hasSize(1)));
	}
	
	@Test
	public void testPost() throws Exception {
		CategoryResource categoryResource = new CategoryResource(null, "Test Category");
		
		mockMvc.perform(post("/api/categories")
				.content(new ObjectMapper().writeValueAsBytes(categoryResource))
				.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"))))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void testPut() throws Exception {
		CategoryResource categoryResource = Translate.to(CategoryResource.class).from(categoryService.findOne(10l));
		
		mockMvc.perform(put("/api/categories/10")
				.content(new ObjectMapper().writeValueAsBytes(categoryResource))
				.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("UTF-8"))))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete("/api/categories/10"))
				.andExpect(status().isNoContent());
	}
}
