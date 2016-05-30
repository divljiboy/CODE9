package com.levi9.taster.core.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.FixedTestTemplate;
import com.levi9.taster.core.Question;

/**
 * JPA implementation of {@link Question}.
 *
 * @author r.horvat
 */
@Entity
@Table(name = "question")
class JpaQuestion extends AbstractJpaObject implements Question {

	private static final long serialVersionUID = 6432444113364596368L;
	
	JpaQuestion() {
		super();
	}

	JpaQuestion(Long id, String content, List<String> tags, Category category, List<Answer> answers) {
		super();
		this.tags = tags;
		this.content = content;
		this.category = category;
		this.id = id;
		this.answers = answers;
	}
	
	@Column
	private String content;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "question_tags", joinColumns = @JoinColumn(name = "question_id"))
	private List<String> tags;
	
	@ManyToOne(targetEntity = JpaCategory.class)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
	
	@OneToMany(targetEntity = JpaAnswer.class, cascade = CascadeType.ALL, mappedBy = "question", orphanRemoval = true)
	private List<Answer> answers;
	
	@ManyToMany(targetEntity = JpaFixedTestTemplate.class, mappedBy = "questions")
	private List<FixedTestTemplate> fixedTestTemplates;

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public List<String> getTags() {
		return tags;
	}

	@Override
	public Category getCategory() {
		return category;
	}

	void setContent(String content) {
		this.content = content;
	}

	void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public List<Answer> getAnswers() {
		return answers;
	}

	void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public List<FixedTestTemplate> getFixedTestTemplates() {
		return fixedTestTemplates;
	}

	void setFixedTestTemplates(List<FixedTestTemplate> fixedTestTemplates) {
		this.fixedTestTemplates = fixedTestTemplates;
	}

	@Override
	public String toString() {
		return "JpaQuestion [content=" + content + ", tags=" + tags
				+ ", category=" + category + "]";
	}
}
