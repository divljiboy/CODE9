package rs.code9.taster.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import rs.code9.taster.model.Category;
import rs.code9.taster.repository.CategoryRepository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC Implementation of Category access layer
 * 
 * @Author s.bogdanovic
 */
@Repository
public class JdbcCategoryRepository implements CategoryRepository {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	private void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @see rs.code9.taster.repository.CategoryRepository#findOne(Long)
	 */
	@Override
	public Category findOne(Long id) {
		List<Category> resultList = jdbcTemplate.query(
				"select * from category where id = ?", new Object[] { id },
				new CategoryRowMapper());
		if (resultList.size() == 1) {
			return resultList.get(0);
		}
		if (resultList.size() > 1) {
			throw new IncorrectResultSizeDataAccessException(1,
					resultList.size());
		}
		return null;
	}

	/**
	 * @see rs.code9.taster.repository.CategoryRepository#findAll()
	 */
	@Override
	public List<Category> findAll() {
		return jdbcTemplate.query("select * from category",
				new CategoryRowMapper());
	}

	/**
	 * @see rs.code9.taster.repository.CategoryRepository#save(rs.code9.taster.model.Category)
	 */
	@Override
	public Category save(Category category) {
		jdbcTemplate.update("replace into category (id, name) values (?, ?)",
				category.getId(), category.getName());
		return category;
	}

	/**
	 * @see rs.code9.taster.repository.CategoryRepository#delete(Long)
	 */
	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from category where id = ?", id);
	}

	/**
	 * RowMapper implementation for Category entity. Converts row result set to
	 * Category.
	 */
	private static final class CategoryRowMapper implements RowMapper<Category> {

		private static final String COLUMN_ID = "id";
		private static final String COLUMN_NAME = "name";

		@Override
		public Category mapRow(ResultSet resultSet, int i) throws SQLException {
			Category category = new Category();
			category.setId(resultSet.getLong(COLUMN_ID));
			category.setName(resultSet.getString(COLUMN_NAME));
			return category;
		}
	}
}