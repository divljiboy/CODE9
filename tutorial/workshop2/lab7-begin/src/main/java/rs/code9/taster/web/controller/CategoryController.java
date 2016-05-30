package rs.code9.taster.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rs.code9.taster.model.Category;
import rs.code9.taster.service.CategoryService;

/**
 * Controller for CRUD operations on categories.
 * 
 * @author d.gajic
 */
@Controller
@RequestMapping("/categories")
public class CategoryController {

	/**
	 * Category service
	 */
	@Autowired
	private CategoryService categoryService;

	/**
	 * Retrieves all categories and returns them as model attribute
	 * <code>categories</code>.
	 * 
	 * @return list of all categories, as model attribute
	 *         <code>categories</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("categories")
	public List<Category> get() {
		return categoryService.findAll();
	}

	/**
	 * Returns the view for adding new category. Adds empty category as model
	 * attribute <code>category</code>.
	 * 
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/editing a category
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		model.addAttribute("category", new Category());
		return "addEditCategory";
	}

	/**
	 * Returns the view for editing a category. Adds category, found by passed
	 * id, as model attribute <code>category</code>.
	 * 
	 * @param id
	 *            the id of the category to edit
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/editing a category
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.findOne(id));
		return "addEditCategory";
	}

	/**
	 * Removes the category object with the specified id.
	 * 
	 * @param id
	 *            the id of the category to remove
	 * @return the redirect view name, which redirects to category list
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long id) {
		categoryService.remove(id);
		return "redirect:..";
	}

	/**
	 * Persists the passed category object.
	 * 
	 * @param category
	 *            the category to persist
	 * @return the redirect view name, which redirects to category list
	 */
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String post(Category category) {
		categoryService.save(category);
		return "redirect:categories";
	}

	/**
	 * Cancels the new/edit category action.
	 * 
	 * @return the redirect view name, which redirects to category list
	 */
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:categories";
	}
}
