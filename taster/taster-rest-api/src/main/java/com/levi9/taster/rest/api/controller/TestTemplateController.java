package com.levi9.taster.rest.api.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codiform.moo.curry.Translate;
import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.TestTemplate;
import com.levi9.taster.core.api.TestTemplateService;
import com.levi9.taster.rest.api.resource.TestTemplateResource;

/**
 * Test template REST controller class.
 * 
 * @author r.horvat
 */
@RestController
@RequestMapping("/api/testTemplates")
public class TestTemplateController {

	/**
	 * the test template service
	 */
	@Inject
	private TestTemplateService<TestTemplate> testTemplateService;
	
	/**
	 * Gets list of abstract test templates.
	 * 
	 * @param number of page
	 * @param size of page
	 * @return list of test templates
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<? extends TestTemplateResource>> list(@RequestParam Integer number, 
			@RequestParam Integer size) {
		Page<? extends TestTemplate> templates = testTemplateService.findAll(number, size);

		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(templates.getTotalElements()));
		return new ResponseEntity<List<? extends TestTemplateResource>>(Translate.to(TestTemplateResource.class).fromEach(templates.getContent()), headers, HttpStatus.OK);
	}
	
	/**
	 * Gets an abstract template by id.
	 * 
	 * @param id of question
	 * @return Test template by id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public TestTemplateResource get(@PathVariable("id") Long id) {
		return Translate.to(TestTemplateResource.class).from(testTemplateService.findOne(id));
	}
	
	/**
	 * Deletes test template by id.
	 * 
	 * @param id of template
	 * @return response entity
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		testTemplateService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
