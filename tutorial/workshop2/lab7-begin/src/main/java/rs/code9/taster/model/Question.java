package rs.code9.taster.model;

/**
 * Question model class.
 * 
 * @author d.gajic
 */
public class Question extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7677167590736866885L;

	/**
	 * Content of the question
	 */
	private String content;

	/**
	 * Category of the question
	 */
	private Category category;

	/**
	 * Default constructor;
	 */
	public Question() {
		super();
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
