package rs.code9.taster.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.code9.taster.model.Category;
import rs.code9.taster.rest.resource.CategoryResource;
import rs.code9.taster.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<CategoryResource> handleGetAllCategories() {
		List<CategoryResource> res = new ArrayList<>();
		for (Category category : categoryService.findAll()) {
			res.add(new CategoryResource(category.getId(), category.getName()));
		}
		return res;
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryResource> handleGetOneCategory(@PathVariable("id") Long id) {
		final Category category = categoryService.getOne(id);
		if (category != null) {
			return new ResponseEntity<>(new CategoryResource(category.getId(), category.getName()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public CategoryResource handlePostCategory(@Valid @RequestBody CategoryResource categoryResource) {
		Category category = new Category();
		category.setName(categoryResource.getName());
		Category saved = categoryService.save(category);
		return new CategoryResource(saved.getId(), saved.getName());
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<byte[]> handleDeleteCategory(@PathVariable("id") Long id) {
		final Category category = categoryService.getOne(id);
		if (category != null) {
			categoryService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
