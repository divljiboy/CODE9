package com.levi9.taster.rest.api.resource;

import java.util.List;

import com.codiform.moo.annotation.CollectionProperty;

/**
 * Fixed test template resource class.
 * 
 * @author r.horvat
 */
public class FixedTestTemplateResource extends TestTemplateResource {
	
	@CollectionProperty(itemTranslation = QuestionResource.class)
	private List<QuestionResource> questions;
	
	public FixedTestTemplateResource() {	
		super();
	}
	
	public FixedTestTemplateResource(Long id, String name, String type) {
		super(id, name, type);
	}

	public List<QuestionResource> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionResource> questions) {
		this.questions = questions;
	}
	
}
