package com.levi9.taster.core;

import com.levi9.taster.commons.model.Identifiable;

/**
 * Represents answer to a question, containing the answer text and 
 * flag which determines correctness of answer.
 * 
 * @author d.gajic
 */
public interface Answer extends Identifiable {

	/**
	 * Content, actually an answer (HTML).
	 * 
	 * @return content as string (not null)
	 */
	String getContent();
	
	/**
	 * True for correct answer.
	 * 
	 * @return correctness of answer
	 */
	Boolean getCorrect();
	
	/**
	 * Question which answer belongs to.
	 * 
	 * @return question (mandatory)
	 */
	Question getQuestion();
}
