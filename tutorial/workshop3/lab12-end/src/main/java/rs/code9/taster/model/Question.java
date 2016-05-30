package rs.code9.taster.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * Question model class.
 * 
 * @author d.gajic
 */
@Entity
@Table(name = "question")
public class Question extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7677167590736866885L;

	/**
	 * Content of the question
	 */
	@Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String content;

	/**
	 * Possible answers to the question
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "question_answer", joinColumns = @JoinColumn(name = "question_id"))
	@OrderColumn(name = "nbr")
	@Valid
	private List<Answer> answers;

	/**
	 * Category of the question
	 */
	@ManyToOne(optional = true)
	@JoinColumn(name = "category_id", nullable = true)
	private Category category;

	/**
	 * Default constructor; initializes the list of answers and adds one answer.
	 */
	public Question() {
		answers = new ArrayList<>();
	}

	/**
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @return category
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
}
