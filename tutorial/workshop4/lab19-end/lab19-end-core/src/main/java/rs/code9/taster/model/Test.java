package rs.code9.taster.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Test is defined by list of questions and meta-jpa including name, create date and creator.
 * 
 * @author d.gajic
 */
@Entity
@Table(name = "test")
public class Test extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4399283467021855173L;

	/**
	 * Name of the test (unique)
	 */
    @Column(nullable = false, length = 255)
	private String name;

	/**
	 * Date of the creation
	 */
    @Column(name="create_date", nullable = false, length = 255)
	private Date createDate;

	/**
	 * Name (username) of the user who created the test
	 */
    @Column(name = "created_by", nullable = false, length = 255)
	private String createdBy;

	/**
	 * List of questions on the test
	 */
    @JoinTable(name = "test_questions",
        joinColumns         = { @JoinColumn(name = "test_fk", referencedColumnName = "id") },
        inverseJoinColumns  = { @JoinColumn(name = "question_fk", referencedColumnName = "id")}
    )
    @ManyToMany(fetch = FetchType.EAGER)
	private List<Question> questions;

	/**
	 * Default constructor; initializes the list of questions to empty list. Sets createDate to current date and time.
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
}
