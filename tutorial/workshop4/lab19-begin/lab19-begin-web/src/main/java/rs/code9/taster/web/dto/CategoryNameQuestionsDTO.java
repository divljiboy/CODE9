package rs.code9.taster.web.dto;

import java.util.List;

import rs.code9.taster.model.Question;

/**
 * DTO for listing questions per category.
 * 
 * @see rs.code9.taster.model.Category
 * @see rs.code9.taster.model.Question
 * 
 * @author p.stanic
 */
public final class CategoryNameQuestionsDTO {

	/**
	 * Name of the category, empty string for uncategorized
	 */
	private String categoryName;

	/**
	 * List of questions associated with the category
	 */
	private List<Question> questions;

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
}
