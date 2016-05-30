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
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.AnswerService;
import com.levi9.taster.core.api.CategoryService;
import com.levi9.taster.core.api.QuestionService;
import com.levi9.taster.rest.api.exception.InvalidInputException;
import com.levi9.taster.rest.api.exception.InvalidInputExceptionExtractor;
import com.levi9.taster.rest.api.resource.AnswerResource;
import com.levi9.taster.rest.api.resource.QuestionResource;
import com.levi9.taster.rest.api.validator.QuestionAnswersValidator;

/**
 * REST Controller for questions.
 * 
 * @author r.horvat
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	@Inject
	private QuestionService questionService;
	
	@Inject
	private CategoryService categoryService;
	
	@Inject
	private AnswerService answerService;
	
	@Inject
	private QuestionAnswersValidator questionAnswersValidator;
	
	/**
	 * Gets list of questions.
	 * 
	 * @param number of page
	 * @param size of page
	 * @return list of questions
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<QuestionResource>> list(@RequestParam Integer number, @RequestParam Integer size) {
		Page<? extends Question> questions = questionService.findAll(number, size);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(questions.getTotalElements()));
		return new ResponseEntity<List<QuestionResource>>(Translate.to(QuestionResource.class).fromEach(questions.getContent()), headers, HttpStatus.OK);
	}
	
	/**
	 * Gets a question by id.
	 * 
	 * @param id of question
	 * @return Question resource by id
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public QuestionResource get(@PathVariable Long id) {
		return Translate.to(QuestionResource.class).from(questionService.findOne(id));
	}
	
	/**
	 * Posts (saves) new question in category.
	 *
	 * @param questionResource the question resource
	 * @param builder the builder
	 * @return saved question resource
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<QuestionResource> post(@Valid @RequestBody final QuestionResource questionResource, BindingResult bindingResult,
			UriComponentsBuilder builder) throws InvalidInputException {
		
		questionAnswersValidator.validate(questionResource, bindingResult);
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);
		
		List<Answer> answers = Lists.newArrayList(Iterables.transform(questionResource.getAnswers(), new Function<AnswerResource, Answer>() {
			@Override
			public Answer apply(AnswerResource input) {
				return answerService.builder(input.getAnswerContent(), input.getCorrect(), null).build();
			}
		}));
		
		Category category = categoryService.findOne(questionResource.getCategoryId());
		Question savedQuestion = questionService.save(
				questionService.builder(questionResource.getContent(), category)
				.tags(questionResource.getTags())
				.answers(answers)
				.build());
		
		for (Answer answer : answers) {
			answerService.update(answer.getId(), answer.getContent(), savedQuestion, answer.getCorrect());
		}
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/questions/{id}").buildAndExpand(savedQuestion.getId()).toUri());
		return new ResponseEntity<QuestionResource>(Translate.to(QuestionResource.class).from(savedQuestion), headers, HttpStatus.CREATED);
	}
	
	/**
	 * Puts (updates) question and all its fields.
	 *
	 * @param questionResource the question resource
	 * @param id of question
	 * @param builder the builder
	 * @return updated question resource
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<QuestionResource> put(@Valid @RequestBody final QuestionResource questionResource, BindingResult bindingResult,
			@PathVariable Long id, UriComponentsBuilder builder) {
		
		questionAnswersValidator.validate(questionResource, bindingResult);
		InvalidInputExceptionExtractor.extractAndThrow(bindingResult);

		List<Answer> answers = Lists.newArrayList(Iterables.transform(questionResource.getAnswers(), new Function<AnswerResource, Answer>() {
			@Override
			public Answer apply(AnswerResource input) {
				return answerService.builder(input.getAnswerContent(), input.getCorrect(), questionService.findOne(questionResource.getId())).id(input.getId()).build();
			}
		}));
		
		Category category = categoryService.findOne(questionResource.getCategoryId());
		Question savedQuestion = questionService.update(id, questionResource.getContent(), category, questionResource.getTags(), answers);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/questions/{id}").buildAndExpand(savedQuestion.getId()).toUri());
		return new ResponseEntity<QuestionResource>(Translate.to(QuestionResource.class).from(savedQuestion), headers, HttpStatus.OK);
	}
	
	/**
	 * Deletes question by id.
	 * 
	 * @param id of question
	 * @return response entity
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
		questionService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Gets list of answers based on question ID.
	 *
	 * @param questionId the question id
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET, value= "/{questionId}/answers")
	public ResponseEntity<List<AnswerResource>> listForQuestion(@PathVariable Long questionId) {
		List<? extends Answer> answers = answerService.findAnswersByQuestionId(questionId);
		
		final HttpHeaders headers = new HttpHeaders();
		headers.add("X-total", String.valueOf(answers.size()));
		return new ResponseEntity<List<AnswerResource>>(Translate.to(AnswerResource.class).fromEach(answers), headers, HttpStatus.OK);
	}
}
