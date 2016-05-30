package rs.code9.taster.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

/**
 * Test is defined by list of questions and meta-data including name, create
 * date and creator.
 * 
 * @author d.gajic
 */
public class Test extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4399283467021855173L;

	/**
	 * Name of the test (unique)
	 */
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String name;

	/**
	 * Date of the creation
	 */
	private Date createDate;

	/**
	 * Name (username) of the user who created the test
	 */
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String createdBy;

	/**
	 * List of questions on the test
	 */
	private List<Question> questions;

	/**
	 * Default constructor; initializes the list of questions to empty list.
	 * Sets createDate to current date and time.
	 */
	public Test() {
		questions = new ArrayList<Question>();
		createDate = new Date();
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	/**
	 * Removes a question from the test.
	 * 
	 * @param question
	 *            the question that is to be removed from the test
	 */
	public void removeQuestion(Question question) {
		this.questions.remove(question);
	}

	/**
	 * Adds a question to the test.
	 * 
	 * @param question
	 *            the question that is to be added to the test
	 */
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
}
