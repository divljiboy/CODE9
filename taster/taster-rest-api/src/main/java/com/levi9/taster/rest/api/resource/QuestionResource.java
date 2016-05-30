package com.levi9.taster.rest.api.resource;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.Property;

/**
 * Question resource class.
 * 
 * @author r.horvat
 */
public class QuestionResource {

	private Long id;

	@NotNull(message = "Content must not be empty.")
	@Size(min = 5, max = 100, message = "Content must be between 5 and 100 characters long.")
	private String content;

	private List<String> tags;

	@Property(translation = "category.id")
	private Long categoryId;

	@Property(translation = "category.name")
	private String categoryName;
	
	@Ignore
	private List<AnswerResource> answers;
	
	public QuestionResource() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<AnswerResource> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerResource> answers) {
		this.answers = answers;
	}
	
}
