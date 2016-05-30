package com.levi9.taster.core.memory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import com.levi9.taster.commons.memory.AbstractMemoryService;
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.Question;
import com.levi9.taster.core.api.QuestionService;

/**
 * In-memory implementation of {@link QuestionService}.
 * 
 * @author m.zorboski
 */
@Named("inMemoryQuestionService")
public class InMemoryQuestionService extends AbstractMemoryService<Question> implements
		QuestionService {

	@Override
	public Question update(Long id, String content, Category category,
			List<String> tags, List<Answer> answers) {
		return save(builder(content, category).id(id).tags(tags).answers(answers).build());
	}

	@Override
	public List<Question> findQuestionsByCategoryId(Long categoryId) {
		List<Question> questionsForCategory = new ArrayList<>();
		
		for (Question question : map.values()) {
			if (question.getCategory().getId().equals(categoryId)) {
				questionsForCategory.add(question);
			}
		}
		
		return questionsForCategory;
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
			this.id = sequence.getAndIncrement();
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
			return new Question() {
				private static final long serialVersionUID = 7335967694146789913L;

				@Override
				public Long getId() {
					return id;
				}

				@Override
				public String getContent() {
					return content;
				}

				@Override
				public List<String> getTags() {
					return tags;
				}

				@Override
				public Category getCategory() {
					return category;
				}

				@Override
				public List<Answer> getAnswers() {
					return answers;
				}
			};
		}
	}
}
