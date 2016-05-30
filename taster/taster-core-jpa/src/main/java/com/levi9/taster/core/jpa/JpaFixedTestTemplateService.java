package com.levi9.taster.core.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;

import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.FixedTestTemplate;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.FixedTestTemplateService;

/**
 * JPA implementation of {@link FixedTestTemplateService}
 * 
 * @author r.horvat
 */
@Named("jpaFixedTestTemplateService")
@Transactional
public class JpaFixedTestTemplateService implements FixedTestTemplateService {

	@Inject
	private FixedTestTemplateJpaRepository templateRepository;
	
	@Inject
	private QuestionJpaRepository questionRepository;
		
	@Override
	public FixedTestTemplate save(FixedTestTemplate fixedTestTemplate) {
		return templateRepository.save((JpaFixedTestTemplate) fixedTestTemplate);
	}

	@Override
	public void delete(Long id) {
		templateRepository.delete(id);
	}

	@Override
	public Page<FixedTestTemplate> findAll(int page, int size) {
		return new JpaPage<FixedTestTemplate>(page * size, size, (int) templateRepository.count(), templateRepository.findAll(new PageRequest(page, size)).getContent());
	}

	@Override
	public FixedTestTemplate findOne(Long id) {
		return templateRepository.findOne(id);
	}
	
	@Override
	public FixedTestTemplate update(Long id, String name, List<Question> questions) {
		JpaFixedTestTemplate template = templateRepository.findOne(id);
		template.setName(name);
		template.getQuestions().clear();
		template.getQuestions().addAll(questions);
		return save(template);
	}
	
	@Override
	public List<Question> findQuestionsById(Long id) {
		return questionRepository.findQuestionsByFixedTestTemplatesId(id);
	}
	
	@Override
	public FixedTestTemplateBuilder builder(String name) {
		return new Builder(name);
	}
	
	private class Builder implements FixedTestTemplateBuilder {
		
		private final String name;
		private List<Question> questions;
		
		private Long id;
		
		public Builder(String name) {
			this.name = name;
			this.questions = new ArrayList<Question>();
		}
		
		public FixedTestTemplateBuilder questions(List<Question> val) {
			this.questions = val;
			return this;
		}
		
		public FixedTestTemplateBuilder id(Long val) {
			id = val;
			return this;
		}
		
		@Override
		public FixedTestTemplate build() {
			return new JpaFixedTestTemplate(id, name, questions);
		}
	}

}
