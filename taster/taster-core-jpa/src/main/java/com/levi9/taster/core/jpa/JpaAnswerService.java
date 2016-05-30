package com.levi9.taster.core.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;

import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.AnswerService;

/**
 * JPA implementation of {@link AnswerService}.
 * 
 * @author r.horvat
 */
@Named("jpaAnswerService")
@Transactional
public class JpaAnswerService implements AnswerService {

	@Inject
	private AnswerJpaRepository answerRepository;
	
	@Override
	public Answer save(Answer t) {
		return answerRepository.save((JpaAnswer)t);
	}

	@Override
	public void delete(Long id) {
		answerRepository.delete(id);
	}

	@Override
	public Page<? extends Answer> findAll(int page, int size) {
		return new JpaPage<Answer>(page * size, size, (int) answerRepository.count(), answerRepository.findAll(new PageRequest(page, size)).getContent());
	}

	@Override
	public Answer findOne(Long id) {
		return answerRepository.findOne(id);
	}

	@Override
	public List<Answer> findAnswersByQuestionId(Long questionId) {
		return answerRepository.findAnswersByQuestionId(questionId);
	}

	@Override
	public Answer update(Long id, String content, Question question,
			Boolean correct) {
		JpaAnswer answer = answerRepository.findOne(id);
		
		answer.setContent(content);
		answer.setCorrect(correct);
		answer.setQuestion(question);
		
		return answer;
	}

	@Override
	public AnswerBuilder builder(String content, Boolean correct, Question question) {
		return new Builder(content, correct, question);
	}

	private class Builder implements AnswerBuilder {
		
		private final String content;
		private final Boolean correct;
		private final Question question;
		
		private Long id;
		
		public Builder(String content, Boolean correct, Question question) {
			this.content = content;
			this.correct = correct;
			this.question = question;
		}
		
		public AnswerBuilder id(Long val) {
			id = val;
			return this;
		}
		
		@Override
		public Answer build() {
			return new JpaAnswer(id, content, correct, question);
		}
	}
}
