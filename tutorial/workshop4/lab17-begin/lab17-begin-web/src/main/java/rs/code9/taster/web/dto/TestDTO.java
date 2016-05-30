package rs.code9.taster.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * DTO for listing tests without questions.
 * 
 * @see rs.code9.taster.model.Test
 * 
 * @author p.stanic
 */
public final class TestDTO {

	/**
	 * id
	 */
	private Long id;

	/**
	 * name
	 */
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String name;

	/**
	 * createDate
	 */
	@NotNull
	private Date createDate;

	/**
	 * createdBy
	 */
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String createdBy;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}
