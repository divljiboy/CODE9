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
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.AnswerService;
import com.levi9.taster.core.api.QuestionService;
import com.levi9.taster.rest.api.exception.InvalidInputExceptionExtractor;
import com.levi9.taster.rest.api.resource.AnswerResource;

/**
 * REST Controller for answers.
 * 
 * @author r.horvat
 */
@RestController
@RequestMapping("/api/answers")
public class AnswerController {

	@Inject
	private AnswerService answerService;
	
	@Inject
	private QuestionService questionService;
	
	/**
	 * Gets list of answers.
	 *
	 * @param number the number
	 * @param size the size
	 * @return list of answers
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AnswerResource>> list(@RequestParam Long questionId, @RequestParam Integer number, @RequestParam Integer size) {
		Page<? extends Answer> answers = answerService.findAll(number, size);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(answers.getTotalElements()));
		return new ResponseEntity<List<AnswerResource>>(Translate.to(AnswerResource.class).fromEach(answers.getContent()), headers, HttpStatus.OK);
	}
	

	
	/**
	 * Gets a answer by id.
	 *
	 * @param answerId the answer id
	 * @return Answer resource by id
	 */
	@RequestMapping(method = RequestMethod.GET, value= "/{id}")
	public AnswerResource get(@PathVariable Long id) {
		return Translate.to(AnswerResource.class).from(answerService.findOne(id));
	}
	
	/**
	 * Posts (saves) new answer.
	 *
	 * @param answerResource the answer resource
	 * @param questionId the question id
	 * @param builder the builder
	 * @return saved answer resource
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<AnswerResource> post(@Valid @RequestBody AnswerResource answerResource, BindingResult bindingResult,
			UriComponentsBuilder builder) {
		
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
		
		Question question = questionService.findOne(answerResource.getQuestionId());
		Answer savedAnswer = answerService.save(
				answerService.builder(answerResource.getAnswerContent(), answerResource.getCorrect(), question)
				.build());
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/answers/{id}").buildAndExpand(savedAnswer.getId()).toUri());
		return new ResponseEntity<AnswerResource>(Translate.to(AnswerResource.class).from(savedAnswer), headers, HttpStatus.CREATED);
	}
	
	/**
	 * Puts (updates) answer and all its fields.
	 * 
	 * @param answerResource the answer resource
	 * @param id of answer
	 * @param builder the builder
	 * @return updated answer resource
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<AnswerResource> put(@Valid @RequestBody AnswerResource answerResource, BindingResult bindingResult,
			@PathVariable Long id, UriComponentsBuilder builder) {
		
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
		
		Question question = questionService.findOne(answerResource.getQuestionId());
		Answer savedAnswer = answerService.update(id, answerResource.getAnswerContent(), question, answerResource.getCorrect());
			
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/answers/{id}").buildAndExpand(savedAnswer.getId()).toUri());
		return new ResponseEntity<AnswerResource>(Translate.to(AnswerResource.class).from(savedAnswer), headers, HttpStatus.OK);
	}
	
	/**
	 * Deletes answer by id.
	 * 
	 * @param id of answer
	 * @return response entity
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		answerService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
