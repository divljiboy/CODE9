package rs.code9.taster.model;

import javax.validation.constraints.Pattern;

/**
 * Answer model class.
 * 
 * @author d.gajic
 */
public class Answer extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8659468310257939759L;

	/**
	 * Answer content
	 */
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String content;

	/**
	 * Indicates correct answer
	 */
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
