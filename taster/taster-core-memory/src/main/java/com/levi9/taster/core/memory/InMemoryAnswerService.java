package com.levi9.taster.core.memory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.levi9.taster.commons.memory.AbstractMemoryService;
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.AnswerService;

/**
 * In-memory implementation of {@link AnswerService}.
 * 
 * @author m.zorboski
 */
@Named("inMemoryAnswerService")
public class InMemoryAnswerService extends AbstractMemoryService<Answer> implements
		AnswerService {
	
	@Override
	public Answer update(Long id, String content, Question question,
			Boolean correct) {
		return save(builder(content, correct, question).id(id).build());
	}

	@Override
	public List<Answer> findAnswersByQuestionId(Long questionId) {
		List<Answer> answersForQuestion = new ArrayList<>();
		
		for (Answer answer : map.values()) {
			if (answer.getQuestion().getId().equals(questionId)) {
				answersForQuestion.add(answer);
			}
		}
		
		return answersForQuestion;
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
			return new Answer() {
				private static final long serialVersionUID = -4110045844462276332L;

				@Override
				public Long getId() {
					return id;
				}

				@Override
				public String getContent() {
					return content;
				}

				@Override
				public Boolean getCorrect() {
					return correct;
				}

				@Override
				public Question getQuestion() {
					return question;
				}
			};
		}
	}
}
