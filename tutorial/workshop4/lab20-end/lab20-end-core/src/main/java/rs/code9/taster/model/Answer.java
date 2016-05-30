package rs.code9.taster.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 * Answer model class.
 * 
 * @author d.gajic
 */
@Entity
@Table(name = "answer")
public class Answer extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8659468310257939759L;

	/**
	 * Answer content
	 */
    @Column(nullable = false, length = 255)
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String content;

	/**
	 * Indicates correct answer
	 */
    @Column(nullable = false)
	private boolean correct;

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
	 * @return correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @param correct
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
}
