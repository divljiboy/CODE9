package com.levi9.taster.core.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.levi9.taster.core.Answer;
import com.levi9.taster.core.Question;

/**
 * JPA implementation of {@link Answer}.
 *
 * @author r.horvat
 */
@Entity
@Table(name = "answer")
class JpaAnswer extends AbstractJpaObject implements Answer {
	
	private static final long serialVersionUID = 4977243159095707502L;

	private String content;
	
	private Boolean correct;
	
	@ManyToOne(targetEntity = JpaQuestion.class)
	@JoinColumn(name = "question_id", nullable = false)
	private Question question;
	
	JpaAnswer() {
		super();
	}

	JpaAnswer(Long id, String content, boolean correct, Question question) {
		super();
		this.id = id;
		this.content = content;
		this.correct = correct;
		this.question = question;
	}
	
	@Override
	public String getContent() {
		return content;
	}

	void setContent(String content) {
		this.content = content;
	}

	@Override
	public Boolean getCorrect() {
		return correct;
	}

	void setCorrect(Boolean correct) {
		this.correct = correct;
	}
	
	@Override
	public Question getQuestion() {
		return question;
	}

	void setQuestion(Question question) {
		this.question = question;
	}
	
	@Override
	public String toString() {
		return "JpaAnswer [content=" + content + ", correct=" + correct
				+ ", question=" + question + ", id=" + id + "]";
	}
}
