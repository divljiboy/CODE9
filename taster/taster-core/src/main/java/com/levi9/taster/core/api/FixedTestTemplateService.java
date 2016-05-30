package com.levi9.taster.core.api;

import java.util.List;

import com.levi9.taster.commons.model.Builder;
import com.levi9.taster.core.FixedTestTemplate;
import com.levi9.taster.core.Question;

/**
 * Fixed test template service interface.
 * 
 * @author r.horvat
 */
public interface FixedTestTemplateService extends TestTemplateService<FixedTestTemplate> {

	/**
	 * Build new test template.
	 *
	 * @param name the name
	 * @return the fixed test template builder
	 */
	FixedTestTemplateBuilder builder(String name);
	
	static interface FixedTestTemplateBuilder extends Builder<FixedTestTemplate> {		
		FixedTestTemplateBuilder id(Long id);
		FixedTestTemplateBuilder questions(List<Question> val);
	}
	
	/**
	 * Updates all fields of the fixed template with specified id.
	 * 
	 * @param id of fixed template to update
	 * @param name the name
	 * @param questions list of questions
	 * @return the updated fixed template
	 */
	FixedTestTemplate update(Long id, String name, List<Question> questions);

	/**
	 * Gets all questions belonging to the fixed template with provided id.
	 * 
	 * @param id of fixed template
	 * @return list of questions
	 */
	List<Question> findQuestionsById(Long id);
	
}

