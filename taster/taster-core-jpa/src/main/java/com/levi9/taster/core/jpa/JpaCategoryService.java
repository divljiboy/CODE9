package com.levi9.taster.core.jpa;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;

import com.levi9.taster.commons.model.Page;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.api.CategoryService;

/**
 * JPA implementation of {@link CategoryService}.
 *
 * @author m.zorboski
 */
@Named("jpaCategoryService")
@Transactional
class JpaCategoryService implements CategoryService {
	
	@Inject
	private CategoryJpaRepository repository;
	
	public Category findByName(String name) {
		return repository.findOne(QJpaCategory.jpaCategory.name.eq(name));
	}

	@Override
	public Category updateName(Long id, String name) {
		JpaCategory category = repository.findOne(id);
		
		category.setName(name);
		
		return save(category);
	}

	@Override
	public Category save(Category category) {
		return repository.save((JpaCategory) category);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Page<Category> findAll(int page, int size) {
		return new JpaPage<Category>(page * size, size, (int) repository.count(), repository.findAll(new PageRequest(page, size)).getContent());
	}

	@Override
	public Category findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public CategoryBuilder builder(String name) {
		return new Builder(name);
	}

	private class Builder implements CategoryBuilder {
		
		private final String name;
		
		private Long id;
		
		public Builder(String name) {
			this.name = name;
		}
		
		public CategoryBuilder id(Long val) {
			id = val;
			return this;
		}
		
		@Override
		public Category build() {
			return new JpaCategory(id, name);
		}
	}
}
