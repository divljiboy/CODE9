package rs.code9.taster.model;

/**
 * Category model class.
 * 
 * @author d.gajic
 */
public class Category extends AbstractBaseEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3537276468739169072L;

	/**
	 * Name of the category
	 */
	private String name;

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
}
