package com.levi9.taster.rest.api.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codiform.moo.curry.Translate;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.FixedTestTemplate;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.CategoryService;
import com.levi9.taster.core.api.FixedTestTemplateService;
import com.levi9.taster.core.api.QuestionService;
import com.levi9.taster.rest.api.exception.InvalidInputExceptionExtractor;
import com.levi9.taster.rest.api.resource.FixedTestTemplateResource;
import com.levi9.taster.rest.api.resource.QuestionResource;

/**
 * REST controller for fixed test templates.
 * 
 * @author r.horvat
 *
 */
@RestController
@RequestMapping("/api/fixedTestTemplates")
public class FixedTestTemplateController {

	/**
	 * the test template service
	 */
	@Inject
	private FixedTestTemplateService testTemplateService;
	
	@Inject 
	private QuestionService questionService;
	
	@Inject
	private CategoryService categoryService;
	
	/**
	 * Gets list of fixed test templates.
	 * 
	 * @param number of page
	 * @param size of page
	 * @return list of test templates
	 */
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FixedTestTemplateResource>> list(@RequestParam Integer number, 
			@RequestParam Integer size) {
		Page<? extends FixedTestTemplate> templates = testTemplateService.findAll(number, size);

		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(templates.getTotalElements()));
		return new ResponseEntity<List<FixedTestTemplateResource>>(Translate.to(FixedTestTemplateResource.class).fromEach(templates.getContent()), headers, HttpStatus.OK);
	}
	
	/**
	 * Gets a fixed test template by id.
	 * 
	 * @param id of question
	 * @return Fixed test template by id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public FixedTestTemplateResource get(@PathVariable("id") Long id) {
		return Translate.to(FixedTestTemplateResource.class).from(testTemplateService.findOne(id));
	}

	/**
	 * Posts (saves) new fixed test template.
	 * 
	 * @param fixedTestTemplateResource the fixed test template resource
	 * @param builder the builder
	 * @return saved fixed test template resource
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FixedTestTemplateResource> post(@Valid @RequestBody FixedTestTemplateResource fixedTestTemplateResource, BindingResult bindingResult,
			UriComponentsBuilder builder) {
		
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);

		List<Question> questions = Lists.newArrayList(Iterables.transform(fixedTestTemplateResource.getQuestions(), new Function<QuestionResource, Question>() {
			@Override
			public Question apply(QuestionResource input) {
				return questionService.builder(input.getContent(), categoryService.findOne(input.getCategoryId())).id(input.getId()).tags(input.getTags()).build();
			}
		}));
		
		FixedTestTemplate savedTemplate = testTemplateService.save(testTemplateService.builder(fixedTestTemplateResource.getName()).questions(questions).build());
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/fixedTestTemplates/{id}").buildAndExpand(savedTemplate.getId()).toUri());
		return new ResponseEntity<FixedTestTemplateResource>(Translate.to(FixedTestTemplateResource.class).from(savedTemplate), headers, HttpStatus.CREATED);
	}
	
	/**
	 * Puts (updates) a fixed test template and all its fields.
	 *
	 * @param testTemplateResource the test template resource resource
	 * @param id of test template
	 * @param builder the builder
	 * @return updated test template resource
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<FixedTestTemplateResource> put(@Valid @RequestBody FixedTestTemplateResource testTemplateResource, BindingResult bindingResult, 
			@PathVariable Long id, UriComponentsBuilder builder) {
		
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);

		List<Question> questions = Lists.newArrayList(Iterables.transform(testTemplateResource.getQuestions(), new Function<QuestionResource, Question>() {
			@Override
			public Question apply(QuestionResource input) {
				return questionService.builder(input.getContent(), categoryService.findOne(input.getCategoryId())).id(input.getId()).tags(input.getTags()).build();
			}
		}));
		
		FixedTestTemplate savedTemplate = testTemplateService.update(id, testTemplateResource.getName(), questions);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/fixedTestTemplates/{id}").buildAndExpand(savedTemplate.getId()).toUri());
		return new ResponseEntity<FixedTestTemplateResource>(Translate.to(FixedTestTemplateResource.class).from(savedTemplate), headers, HttpStatus.OK);
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
	
	/**
	 * @param id
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{templateId}/questions")
	public ResponseEntity<List<QuestionResource>> listQuestions(@PathVariable Long templateId) {
		List<Question> questionsList = testTemplateService.findQuestionsById(templateId);
		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(questionsList.size()));
		return new ResponseEntity<List<QuestionResource>>(Translate.to(QuestionResource.class).fromEach(questionsList), headers, HttpStatus.OK);
	}
}
