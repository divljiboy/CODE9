package com.levi9.taster.core.api;

import com.levi9.taster.commons.model.Builder;
import com.levi9.taster.commons.service.CrudService;
import com.levi9.taster.core.Category;

/**
 * Category service
 * 
 * @author d.gajic
 */
public interface CategoryService extends CrudService<Category> {
	
	/**
	 * Build new category.
	 *
	 * @param name the name
	 * @return the category builder
	 */
	CategoryBuilder builder(String name);
	
	static interface CategoryBuilder extends Builder<Category> {		
		CategoryBuilder id(Long id);		
	}
	
	Category updateName(Long id, String name);
	
}
