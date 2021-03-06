package rs.code9.taster.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.code9.taster.model.Category;
import rs.code9.taster.repository.CategoryRepository;
import rs.code9.taster.service.CategoryService;

import javax.transaction.Transactional;

import java.util.List;

/**
 * {@link CategoryService} implementation.
 *
 * @author s.bogdanovic
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * @see rs.code9.taster.service.CategoryService#findOne(Long)
	 */
	@Override
	public Category findOne(Long id) {
		return categoryRepository.findOne(id);
	}

	/**
	 * @see rs.code9.taster.service.CategoryService#findAll()
	 */
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	/**
	 * @see rs.code9.taster.service.CategoryService#save(rs.code9.taster.model.AbstractBaseEntity)
	 */
	@Override
	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * @see rs.code9.taster.service.CategoryService#remove(Long)
	 */
	@Override
	public void remove(Long id) throws IllegalArgumentException {
		Category category = categoryRepository.findOne(id);
		if (category == null) {
			throw new IllegalArgumentException(String.format(
					"Category with id=%d does not exist.", id));
		}
		categoryRepository.delete(id);
	}
}
