package com.levi9.taster.core.api;

import java.util.List;

import com.levi9.taster.commons.model.Builder;
import com.levi9.taster.commons.service.CrudService;
import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Question;

public interface AnswerService extends CrudService<Answer> {

	/**
	 * Build new answer.
	 *
	 * @param content the content
	 * @param correct the correct
	 * @param question the question
	 * @return the answer builder
	 */
	AnswerBuilder builder(String content, Boolean correct, Question question);
	
	static interface AnswerBuilder extends Builder<Answer> {		
		AnswerBuilder id(Long id);
	}
	
	/**
	 * Updates all fields of answer with provided id.
	 * 
	 * @param id of answer to update
	 * @param content the content
	 * @param question the question
	 * @param correct correctness of answer
	 * @return the updated answer
	 */
	Answer update(Long id, String content, Question question, Boolean correct);
	
	/**
	 * Gets all answers belonging to question with provided id.
	 *
	 * @param questionId the question id
	 * @return list of questions
	 */
	List<Answer> findAnswersByQuestionId(Long questionId);
	
}
