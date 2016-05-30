package com.levi9.taster.core.jpa;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.levi9.taster.core.FixedTestTemplate;
import com.levi9.taster.core.Question;


/**
 * JPA implementation of {@link FixedTestTemplate}.
 * 
 * @author r.horvat
 */
@Entity
@DiscriminatorValue("F")
@Table(name = "test_template_fixed")
public class JpaFixedTestTemplate extends JpaTestTemplate implements FixedTestTemplate {

	private static final long serialVersionUID = -5059665181172602717L;
	
	@JoinTable(name = "test_template_fixed_questions",
		joinColumns         = { @JoinColumn(name = "test_template_id", referencedColumnName = "id") },
		inverseJoinColumns  = { @JoinColumn(name = "question_id", referencedColumnName = "id") }
	)
	@ManyToMany(targetEntity = JpaQuestion.class, fetch = FetchType.EAGER)
	private List<Question> questions;
	
	public JpaFixedTestTemplate() {
		super();
	}

	public JpaFixedTestTemplate(Long id, String name, List<Question> questions) {
		super();
		this.id = id;
		this.name = name;
		this.questions = questions;
	}
	
	/* (non-Javadoc)
	 * @see com.levi9.taster.core.TestTemplate#getType()
	 */
	public String getType() {
		return "F";
	}

	@Override
	public List<Question> getQuestions() {
		return questions;
	}

	void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	
	@Override
	public String toString() {
		return "JpaFixedTestTemplate [questions=" + questions + ", name="
				+ name + ", id=" + id + "]";
	}
	
}
