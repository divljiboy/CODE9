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
import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.CategoryService;
import com.levi9.taster.core.api.QuestionService;
import com.levi9.taster.rest.api.exception.InvalidInputException;
import com.levi9.taster.rest.api.exception.InvalidInputExceptionExtractor;
import com.levi9.taster.rest.api.resource.CategoryResource;
import com.levi9.taster.rest.api.resource.QuestionResource;

/**
 * REST Controller for categories.
 *
 * @author d.gajic
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private QuestionService questionService;
	
	/**
	 * Gets categories.
	 *
	 * @param number the number
	 * @param size the size
	 * @return the list
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryResource>> list(@RequestParam Integer number, @RequestParam Integer size) {
		Page<? extends Category> categories = categoryService.findAll(number, size);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(categories.getTotalElements()));
		return new ResponseEntity<List<CategoryResource>>(Translate.to(CategoryResource.class).fromEach(categories.getContent()), headers, HttpStatus.OK);
	}
	
	/**
	 * Gets the single category based on ID.
	 *
	 * @param id the id
	 * @return the category resource
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public CategoryResource get(@PathVariable Long id) {
		return Translate.to(CategoryResource.class).from(categoryService.findOne(id));
	}
	
	/**
	 * Posts/creates new category.
	 *
	 * @param categoryResource the category resource
	 * @param builder the builder
	 * @return the new saved category
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CategoryResource> post(@Valid @RequestBody CategoryResource categoryResource, BindingResult bindingResult,
			UriComponentsBuilder builder) throws InvalidInputException {
		
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
		
		Category savedCategory = categoryService.save(categoryService.builder(categoryResource.getName()).build());
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/categories/{id}").buildAndExpand(savedCategory.getId()).toUri());
		return new ResponseEntity<CategoryResource>(Translate.to(CategoryResource.class).from(savedCategory), headers, HttpStatus.CREATED);
	}
	
	/**
	 * Updates single category and all its properties.
	 *
	 * @param categoryResource the category resource
	 * @param id the id
	 * @param builder the builder
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<CategoryResource> put(@Valid @RequestBody CategoryResource categoryResource, BindingResult bindingResult, @PathVariable Long id,
			UriComponentsBuilder builder) throws InvalidInputException {
		
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
		
		Category savedCategory = categoryService.updateName(id, categoryResource.getName());
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/categories/{id}").buildAndExpand(savedCategory.getId()).toUri());
		return new ResponseEntity<CategoryResource>(Translate.to(CategoryResource.class).from(savedCategory), headers, HttpStatus.OK);
	}
	
	/**
	 * Patches/updates only the name of category.
	 *
	 * @param categoryResource the category resource
	 * @param id the id
	 * @param builder the builder
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.PATCH, value = "/{id}")
	public ResponseEntity<CategoryResource> patch(@Valid @RequestBody CategoryResource categoryResource, @PathVariable Long id,
			UriComponentsBuilder builder) {
		Category savedCategory = categoryService.updateName(id, categoryResource.getName());
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/categories/{id}").buildAndExpand(savedCategory.getId()).toUri());
		return new ResponseEntity<CategoryResource>(Translate.to(CategoryResource.class).from(savedCategory), headers, HttpStatus.OK);
	}
	
	/**
	 * Delete category based on ID.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		categoryService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Gets list of questions based on category ID.
	 * 
	 * @param categoryId id of category
	 * @return list of question resources
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{categoryId}/questions")
	public ResponseEntity<List<QuestionResource>> listForCategory(@PathVariable Long categoryId) {
		List<? extends Question> questions = questionService.findQuestionsByCategoryId(categoryId);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(questions.size()));
		return new ResponseEntity<List<QuestionResource>>(Translate.to(QuestionResource.class).fromEach(questions), headers, HttpStatus.OK);
	}
}
