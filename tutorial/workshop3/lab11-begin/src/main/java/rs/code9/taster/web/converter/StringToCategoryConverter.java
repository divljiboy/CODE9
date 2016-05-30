package rs.code9.taster.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import rs.code9.taster.model.Category;
import rs.code9.taster.service.CategoryService;

/**
 * Converts string to category object. String is id (long).
 * 
 * @author p.stanic
 */
public class StringToCategoryConverter implements Converter<String, Category> {

	/**
	 * Category service is used to look up the category based on id
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Category convert(String categoryId) {
		return categoryService.findOne(Long.parseLong(categoryId));
	}
}
