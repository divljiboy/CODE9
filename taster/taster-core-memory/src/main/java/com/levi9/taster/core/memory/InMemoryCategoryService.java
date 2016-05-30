package com.levi9.taster.core.memory;

import javax.inject.Named;

import com.levi9.taster.commons.memory.AbstractMemoryService;
import com.levi9.taster.core.Category;
import com.levi9.taster.core.api.CategoryService;

/**
 * In-memory implementation of {@link CategoryService}.
 * 
 * @author d.gajic
 */
@Named("inMemoryCategoryService")
public class InMemoryCategoryService extends AbstractMemoryService<Category> implements CategoryService {

	@Override
	public CategoryBuilder builder(String name) {
		return new Builder(name);
	}

	private class Builder implements CategoryBuilder {
		
		private final String name;
		
		private Long id;
		
		public Builder(String name) {
			this.name = name;
			this.id = sequence.getAndIncrement();
		}
		
		public CategoryBuilder id(Long val) {
			id = val;
			return this;
		}
		
		@Override
		public Category build() {
			return new Category() {
				private static final long serialVersionUID = 6871996353733342059L;

				@Override
				public Long getId() {					
					return id;
				}
				
				@Override
				public String getName() {
					return name;
				}
			};
		}		
	}

	@Override
	public Category updateName(Long id, String name) {
		return save(builder(name).id(id).build());
	}
}
