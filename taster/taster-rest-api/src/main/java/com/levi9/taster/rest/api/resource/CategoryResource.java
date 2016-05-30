package com.levi9.taster.rest.api.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Resource that represents category.
 *
 * @author d.gajic
 */
public class CategoryResource {
	
	private Long id;
	
	@NotNull(message = "Category name must not be empty.")
	@Size(min = 1, max = 30, message = "Category name must be between 1 and 30 characters long") //TODO: i18n
	private String name;
	
	public CategoryResource() {
	}
	
	public CategoryResource(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
