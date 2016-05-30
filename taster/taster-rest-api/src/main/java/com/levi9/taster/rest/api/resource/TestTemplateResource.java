package com.levi9.taster.rest.api.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TestTemplateResource {

	private Long id;
	
	@NotNull(message = "Test template name must not be empty.")
	@Size(min = 1, max = 30, message = "Test template name must be between 1 and 30 characters long.")
	private String name;
	
	protected String type;
	
	public TestTemplateResource() {
	}

	public TestTemplateResource(Long id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
