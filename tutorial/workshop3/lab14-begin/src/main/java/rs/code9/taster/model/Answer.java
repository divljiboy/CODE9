package rs.code9.taster.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;

/**
 * Answer model class.
 * 
 * @author d.gajic
 */
@Embeddable
public class Answer implements Serializable {

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
