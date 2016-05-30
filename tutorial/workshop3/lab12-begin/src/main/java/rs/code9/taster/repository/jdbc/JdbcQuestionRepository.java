package rs.code9.taster.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import rs.code9.taster.model.Answer;
import rs.code9.taster.model.Category;
import rs.code9.taster.model.Question;
import rs.code9.taster.repository.QuestionRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC Implementation of Question access layer
 * 
 * @author s.bogdanovic
 * @author r.zuljevic
 */
@Repository
public class JdbcQuestionRepository implements QuestionRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @see rs.code9.taster.repository.QuestionRepository#findQuestionsByCategoryId(Long)
	 */
	@Override
	public List<Question> findQuestionsByCategoryId(Long id) {
		QuestionRowCallbackHandler questionRowCallbackHandler = new QuestionRowCallbackHandler();
		jdbcTemplate
				.query("select q.id, q.content, c.id, c.name, a.id, a.content, a.correct from question q "
						+ "left join category c on q.category_id = c.id "
						+ "left join answer a on a.question_id = q.id "
						+ "where c.id = ?", questionRowCallbackHandler, id);
		return questionRowCallbackHandler.getQuestions();
	}

	/**
	 * @see rs.code9.taster.repository.QuestionRepository#findQuestionsByTestId(Long)
	 */
	public List<Question> findQuestionsByTestId(Long id) {
		QuestionRowCallbackHandler questionRowCallbackHandler = new QuestionRowCallbackHandler();
		jdbcTemplate
				.query("select q.id, q.content, c.id, c.name, a.id, a.content, a.correct from question q "
						+ "left join category c on q.category_id = c.id "
						+ "left join answer a on a.question_id = q.id "
						+ "right join test_questions tq on tq.question_id = q.id "
						+ "where tq.test_id = ? ", questionRowCallbackHandler,
						id);
		return questionRowCallbackHandler.getQuestions();
	}

	/**
	 * @see rs.code9.taster.repository.QuestionRepository#findOne(Long)
	 */
	@Override
	public Question findOne(Long id) {
		QuestionRowCallbackHandler questionRowCallbackHandler = new QuestionRowCallbackHandler();
		jdbcTemplate
				.query("select q.id, q.content, c.id, c.name, a.id, a.content, a.correct from question q "
						+ "left join category c on q.category_id = c.id "
						+ "left join answer a on a.question_id = q.id "
						+ "where q.id = ?", questionRowCallbackHandler, id);
		return questionRowCallbackHandler.getQuestion();
	}

	/**
	 * @see rs.code9.taster.repository.QuestionRepository#findAll()
	 */
	@Override
	public List<Question> findAll() {
		QuestionRowCallbackHandler questionRowCallbackHandler = new QuestionRowCallbackHandler();
		jdbcTemplate
				.query("select q.id, q.content, c.id, c.name, a.id, a.content, a.correct from question q "
						+ "left join category c on q.category_id = c.id "
						+ "left join answer a on a.question_id = q.id ",
						questionRowCallbackHandler);
		return questionRowCallbackHandler.getQuestions();
	}

	/**
	 * @see rs.code9.taster.repository.QuestionRepository#save(rs.code9.taster.model.Question)
	 */
	@Override
	public Question save(final Question question) {
		KeyHolder holder = new GeneratedKeyHolder();
		if (question.getId() != null) {
			Long catId = null;

			if (question.getCategory() != null) {
				catId = question.getCategory().getId();
			}

			jdbcTemplate
					.update("update question set content = ?, category_id = ? where id = ?",
							question.getContent(), catId, question.getId());
		} else {
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection
							.prepareStatement(
									"insert into question (content, category_id) values (?, ?)",
									Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, question.getContent());
					if (question.getCategory() != null) {
						ps.setLong(2, question.getCategory().getId());
					} else {
						ps.setNull(2, Types.BIGINT);
					}
					return ps;
				}
			}, holder);
			question.setId(holder.getKey().longValue());
		}
		updateAnswers(question);
		return question;
	}

	/**
	 * @see rs.code9.taster.repository.QuestionRepository#delete(Long)
	 */
	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from question where id = ?", id);
	}

	/**
	 * Helper implementation for updating list of answers for a question. It
	 * first removes answers that are no longer in the list, and than inserts
	 * the new records or updates existing.
	 * 
	 * @param question
	 *            the Question which contains answers to be updated
	 */
	private void updateAnswers(Question question) {
		List<Answer> existingAnswers = jdbcTemplate
				.query("select id, content, correct from answer where question_id = ?",
						new AnswerRowMapper(), question.getId());
		for (Answer existingAnswer : existingAnswers) {
			if (!question.getAnswers().contains(existingAnswer)) {
				jdbcTemplate.update(
						"delete from answer where question_id = ? and id = ?",
						question.getId(), existingAnswer.getId());
			}
		}
		for (Answer answer : question.getAnswers()) {
			jdbcTemplate
					.update("replace into answer (id, content, correct, question_id) values (?, ?, ?, ?)",
							answer.getId(), answer.getContent(),
							answer.isCorrect(), question.getId());
		}
	}

	/**
	 * RowCallbackHandler for mapping join query results into graph of Question,
	 * Category and Answer entities.
	 */
	private static final class QuestionRowCallbackHandler implements
			RowCallbackHandler {

		private List<Question> result = new ArrayList<>();

		private Question currentQuestion = null;
		private Category currentCategory = null;
		private Answer currentAnswer = null;

		@Override
		public void processRow(ResultSet resultSet) throws SQLException {
			final Long questionId = resultSet.getLong("q.id");
			if (currentQuestion == null
					|| !questionId.equals(currentQuestion.getId())) {
				currentQuestion = new Question();
				result.add(currentQuestion);
				currentQuestion.setId(questionId);
				currentQuestion.setContent(resultSet.getString("q.content"));
			}

			final Long categoryId = resultSet.getLong("c.id");
			if (currentCategory == null
					|| !categoryId.equals(currentCategory.getId())) {
				currentCategory = new Category();
				currentCategory.setId(categoryId);
				currentCategory.setName(resultSet.getString("c.name"));
				currentQuestion.setCategory(currentCategory);
			}

			final Long answerId = resultSet.getLong("a.id");
			if (currentAnswer == null
					|| !answerId.equals(currentAnswer.getId())) {
				currentAnswer = new Answer();
				currentAnswer.setId(answerId);
				currentAnswer.setContent(resultSet.getString("a.content"));
				currentAnswer.setCorrect(resultSet.getBoolean("a.correct"));
				currentQuestion.getAnswers().add(currentAnswer);
			}
		}

		public Question getQuestion() {
			if (result.isEmpty()) {
				return null;
			}
			if (result.size() > 1) {
				throw new IllegalStateException(
						"More than one question in a result.");
			}
			return result.get(0);
		}

		public List<Question> getQuestions() {
			return result;
		}
	}

	/**
	 * Answer row mapper which converts query result set into Answer instance.
	 */
	private static final class AnswerRowMapper implements RowMapper<Answer> {

		@Override
		public Answer mapRow(ResultSet resultSet, int i) throws SQLException {
			Answer answer = new Answer();
			answer.setId(resultSet.getLong("id"));
			answer.setContent(resultSet.getString("content"));
			answer.setCorrect(resultSet.getBoolean("correct"));
			return answer;
		}
	}
}
