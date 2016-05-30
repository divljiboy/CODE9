package com.levi9.taster.core;

import java.util.List;

import com.levi9.taster.commons.model.Identifiable;

/**
 * The question has content (typically HTML), list of possible answers,
 * it belongs to a {@link Category} and has list of tags (used for grouping).
 * 
 * @author d.gajic
 */
public interface Question extends Identifiable {
	
	/**
	 * Content, actually a question (HTML).
	 * 
	 * @return content as string (not null)
	 */
	String getContent();
	
	/**
	 * List of tags used for grouping.
	 * 
	 * @return list of tags if there are any, empty list otherwise
	 */
	List<String> getTags();
	
	/**
	 * Category o which question belongs to.
	 * 
	 * @return category (mandatory)
	 */
	Category getCategory();
	
	/**
	 * List of answers for question.
	 * 
	 * @return list of answers
	 */
	List<Answer> getAnswers();
	
}
