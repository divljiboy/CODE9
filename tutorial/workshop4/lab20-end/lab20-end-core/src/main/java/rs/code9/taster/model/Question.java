package rs.code9.taster.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
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
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "question_fk", referencedColumnName = "id", nullable = false)
	@Valid
	private List<Answer> answers;

	/**
	 * Category of the question
	 */
    @ManyToOne
    @JoinColumn(name = "category_fk", nullable = false)
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
