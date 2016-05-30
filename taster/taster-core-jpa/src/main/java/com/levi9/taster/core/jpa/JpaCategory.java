package com.levi9.taster.core.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.levi9.taster.core.Category;
import com.levi9.taster.core.Question;

/**
 * JPA implementation of {@link Category}.
 *
 * @author m.zorboski
 */
@Entity
@Table(name = "category")
class JpaCategory extends AbstractJpaObject implements Category {

	private static final long serialVersionUID = 3430732866912015455L;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, targetEntity = JpaQuestion.class, orphanRemoval = true)
	private Set<Question> questions;
	
	JpaCategory() {
		super();
	}

	JpaCategory(String name) {
		this();
		this.name = name;
	}
	
	JpaCategory(Long id, String name) {
		this();
		this.id = id;
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "JpaCategory [name=" + name + ", id=" + id + "]";
	}
}