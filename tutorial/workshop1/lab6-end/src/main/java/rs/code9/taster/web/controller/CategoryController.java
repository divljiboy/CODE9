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

@Controller
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("categories")
	public List<Category> get() {
		return categoryService.findAll();
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		model.addAttribute("category", new Category());
		return "addEditCategory";
	}	
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("category", categoryService.findOne(id));
		return "addEditCategory";
	}	
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long id) {
		categoryService.remove(id);
		return "redirect:..";
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(Category category) {
		categoryService.save(category);
		return "redirect:categories";
	}	
}
