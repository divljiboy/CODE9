package com.levi9.taster.core;

import java.util.List;


/**
 * Represents a fixed test template, which includes a fixed list of questions.
 * 
 * @author r.horvat
 */
public interface FixedTestTemplate extends TestTemplate {
	
	/**
	 * List of questions in a template.
	 * 
	 * @return list of questions
	 */
	List<Question> getQuestions();
}
