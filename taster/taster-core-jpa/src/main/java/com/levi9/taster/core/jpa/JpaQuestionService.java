package com.levi9.taster.core.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;

import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.QuestionService;

/**
 * JPA implementation of {@link QuestionService}.
 * 
 * @author r.horvat
 */
@Named("jpaQuestionService")
@Transactional
public class JpaQuestionService implements QuestionService {

	@Inject
	private QuestionJpaRepository questionRepository;
	
	@Inject 
	private FixedTestTemplateJpaRepository templateRepository;
	
	@Override
	public Question save(Question question) {
		return questionRepository.save((JpaQuestion) question);
	}

	@Override
	public void delete(Long id) {
		Question question = questionRepository.findOne(id);

		List<JpaFixedTestTemplate> templates = templateRepository.findByQuestionsId(id);
		
		for (JpaFixedTestTemplate template : templates) {
			template.getQuestions().remove(question);
			templateRepository.save(template);
		}

		questionRepository.delete(id);
	}

	@Override
	public Page<Question> findAll(int page, int size) {
		return new JpaPage<Question>(page * size, size, (int) questionRepository.count(), questionRepository.findAll(new PageRequest(page, size)).getContent());
	}

	@Override
	public Question findOne(Long id) {
		return questionRepository.findOne(id);
	}
	
	@Override
	public Question update(Long id, String content, Category category,
			List<String> tags, List<Answer> answers) {
		JpaQuestion question = questionRepository.findOne(id);
		
		question.setContent(content);
		question.setTags(tags);
		question.setCategory(category);
		
		question.getAnswers().clear();
		question.getAnswers().addAll(answers);
		
		return questionRepository.save(question);
	}

	@Override
	public List<Question> findQuestionsByCategoryId(Long categoryId) {
		return questionRepository.findQuestionsByCategoryId(categoryId);
	}
	
	@Override
	public QuestionBuilder builder(String content, Category category) {
		return new Builder(content, category);
	}
	
	private class Builder implements QuestionBuilder {
		
		private final String content;
		private final Category category;
		private List<String> tags;
		private List<Answer> answers;
		
		private Long id;
		
		public Builder(String content, Category category) {
			this.content = content;
			this.category = category;
		}
		
		public QuestionBuilder tags(List<String> val) {
			tags = val;
			return this;
		}
		
		public QuestionBuilder id(Long val) {
			id = val;
			return this;
		}
		
		public QuestionBuilder answers(List<Answer> val) {
			answers = val;
			return this;
		}
		
		@Override
		public Question build() {
			JpaQuestion question = new JpaQuestion(id, content, tags, category, answers);
			
			if (question.getAnswers() != null) {
				for (Answer answer : question.getAnswers()) {
					if (answer.getQuestion() == null) {
						((JpaAnswer) answer).setQuestion(question);
					}
				}
			}
			return question;
		}
	}
}
