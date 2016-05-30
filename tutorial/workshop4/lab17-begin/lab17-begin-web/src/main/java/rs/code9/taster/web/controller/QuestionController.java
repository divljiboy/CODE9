package rs.code9.taster.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import rs.code9.taster.model.Category;
import rs.code9.taster.model.Question;
import rs.code9.taster.service.CategoryService;
import rs.code9.taster.service.QuestionService;
import rs.code9.taster.web.dto.CategoryNameQuestionsDTO;
import rs.code9.taster.web.validator.QuestionAnswersValidator;

/**
 * Controller for CRUD operations on questions.
 * 
 * @author p.stanic
 */
@Controller
@RequestMapping("/questions")
public class QuestionController {

	/**
	 * Question service
	 */
	@Autowired
	private QuestionService questionService;

	/**
	 * Category service
	 */
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private QuestionAnswersValidator questionAnswersValidator;

	/**
	 * Retrieves all questions per category and returns them as model attribute <code>questions</code>.
	 * 
	 * @return list of category name questions DTOs, as model attribute <code>questions</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("questions")
	public List<CategoryNameQuestionsDTO> get() {
		Map<String, CategoryNameQuestionsDTO> retVal = new HashMap<>();
		List<Question> questions = questionService.findAll();
		for (Question question : questions) {
			Category category = question.getCategory();
			// if category is null set questionName to "", meaning uncategorized
			String categoryName = category == null ? "" : category.getName();
			CategoryNameQuestionsDTO dto = retVal.get(categoryName);
			if (dto == null) {
				dto = new CategoryNameQuestionsDTO();
				dto.setCategoryName(categoryName);
				dto.setQuestions(new ArrayList<Question>());
				retVal.put(categoryName, dto);
			}
			dto.getQuestions().add(question);
		}
		return new ArrayList<>(retVal.values());
	}

	/**
	 * Returns the view for adding new question. Adds empty question as model attribute <code>question</code> and list
	 * of all categories as model attribute <code>categories</code>.
	 * 
	 * @param model the model object map
	 * @return the name of the view for adding/editing a question
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		model.addAttribute("question", new Question());
		model.addAttribute("categories", categoryService.findAll());
		return "addEditQuestion";
	}

	/**
	 * Returns the view for editing a question. Adds question, found by passed id, as model attribute
	 * <code>question</code> and list of all categories as model attribute <code>categories</code>.
	 * 
	 * @param id the id of the question to edit
	 * @param model the model object map
	 * @return the name of the view for adding/editing a question
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("question", questionService.getOne(id));
		model.addAttribute("categories", categoryService.findAll());
		return "addEditQuestion";
	}

	/**
	 * Removes the question object with the specified id.
	 * 
	 * @param id the id of the question to remove
	 * @return the redirect view name, which redirects to question list
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long id) {
		questionService.remove(id);
		return "redirect:..";
	}

	/**
	 * Persists the passed question object.
	 * 
	 * @param question the question to persist
	 * @param bindingResult the binding result
	 * @param model the model object map
	 * @return the redirect view name, which redirects to question list
	 */
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String post(@Valid Question question, BindingResult bindingResult, Model model) {
		String viewName;
		questionAnswersValidator.validate(question, bindingResult);
		if (!bindingResult.hasErrors()) {
			questionService.save(question);
			viewName = "redirect:questions";
		} else {
			model.addAttribute("question", question);
			model.addAttribute("categories", categoryService.findAll());
			viewName = "addEditQuestion";
		}
		return viewName;
	}

	/**
	 * Cancels the new/edit question action.
	 * 
	 * @return the redirect view name, which redirects to question list
	 */
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:questions";
	}

	/**
	 * Adds new answer to question. Adds list of all categories as model attribute <code>categories</code>.
	 * 
	 * @param question the question to add answer to
	 * @param model the model object map
	 * @return the name of the view for adding/editing a question
	 */
	@RequestMapping(params = "addAnswer", method = RequestMethod.POST)
	public String addAnswer(Question question, Model model) {
		questionService.addAnswer(question);
		model.addAttribute("categories", categoryService.findAll());
		return "addEditQuestion";
	}

	/**
	 * Removes answer from the question. Adds list of all categories as model attribute <code>categories</code>.
	 * 
	 * @param question the question to remove the answer from
	 * @param id the id of the answer to remove
	 * @param model the model object map
	 * @return the name of the view for adding/editing a question
	 */
	@RequestMapping(params = "removeAnswer", method = RequestMethod.POST)
	public String removeAnswer(Question question, @RequestParam(value = "removeAnswer") Long answerId,
			Model model) {
		questionService.removeAnswer(question, answerId);
		model.addAttribute("categories", categoryService.findAll());
		return "addEditQuestion";
	}
}
