package com.levi9.taster.rest.api.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.codiform.moo.annotation.Property;

/**
 * Resource that represents answer.
 * 
 * @author r.horvat
 */
public class AnswerResource {

	private Long id;
	
	@NotNull(message = "Answer content must not be empty.")
	@Size(min = 1, max = 100, message = "Content must be between 1 and 100 characters long.")
	@Property(translation = "content")
	private String answerContent;
	
	private Boolean correct;
	
	@Property(translation = "question.id")
	private Long questionId;
	
	public AnswerResource() {	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Long questionId) {
		this.questionId = questionId;
	}
}
