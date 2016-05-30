package rs.code9.taster.service;

import rs.code9.taster.model.Question;
import rs.code9.taster.model.Test;

/**
 * Service for test management.
 * 
 * @author p.stanic
 */
public interface TestService extends CrudService<Test> {

	/**
	 * Adds the question to the test.
	 * 
	 * @param test the test to which the question is added
	 * @param question the question that is added to the test
	 * @throws IllegalArgumentException thrown when question is null or test already contains the provided question
	 */
	void addQuestion(Test test, Question question) throws IllegalArgumentException;

	/**
	 * Removes the question from the test.
	 * 
	 * @param test the test from which the question is removed
	 * @param question the question that is removed from the test
	 * @throws IllegalArgumentException thrown when question is null or test does not contain the provided question
	 */
	void removeQuestion(Test test, Question question) throws IllegalArgumentException;
}
