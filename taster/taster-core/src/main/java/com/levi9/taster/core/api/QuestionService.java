package com.levi9.taster.core.api;

import java.util.List;

import com.levi9.taster.commons.model.Builder;
import com.levi9.taster.commons.service.CrudService;
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.Question;

/**
 * Question service interface.
 * 
 * @author r.horvat
 */
public interface QuestionService extends CrudService<Question> {

	/**
	 * Build new question.
	 *
	 * @param content the content
	 * @param category the category
	 * @return the question builder
	 */
	QuestionBuilder builder(String content, Category category);
	
	static interface QuestionBuilder extends Builder<Question> {		
		QuestionBuilder id(Long id);
		QuestionBuilder tags(List<String> val);
		QuestionBuilder answers(List<Answer> val);
	}
	
	/**
	 * Updates all fields of question with provided id.
	 * 
	 * @param id of question to update
	 * @param content the content
	 * @param category the category
	 * @param tags list of new tags
	 * @param answers list of answers
	 * @return the updated question
	 */
	Question update(Long id, String content, Category category, List<String> tags, List<Answer> answers);
	
	/**
	 * Gets all questions belonging to category with provided id.
	 *
	 * @param categoryId the category id
	 * @return list of questions
	 */
	List<Question> findQuestionsByCategoryId(Long categoryId);
	
}
