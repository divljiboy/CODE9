package rs.code9.taster.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import rs.code9.taster.model.Question;
import rs.code9.taster.model.Test;
import rs.code9.taster.service.QuestionService;
import rs.code9.taster.service.TestService;

/**
 * Controller for CRUD operations on tests.
 * 
 * @author p.stanic
 * @author r.zuljevic
 */
@Controller
@RequestMapping("/tests")
public class TestController {

	/**
	 * Question service
	 */
	@Autowired
	private QuestionService questionService;

	/**
	 * Test service
	 */
	@Autowired
	private TestService testService;

	/**
	 * Retrieves all tests and returns them as model attribute
	 * <code>tests</code>.
	 * 
	 * @return list of all tests, as model attribute <code>tests</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("tests")
	public List<Test> get() {
		return testService.findAll();
	}

	/**
	 * Returns the view for adding new test. Adds empty test as model attribute
	 * <code>test</code>.
	 * 
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/editing a test
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		Test test = new Test();
		test.setCreateDate(new Date());
		model.addAttribute("test", test);
		return "addEditTest";
	}

	/**
	 * Returns the view for editing a test. Adds test, found by passed id, as
	 * model attribute <code>test</code>.
	 * 
	 * @param id
	 *            the id of the test to edit
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/editing a test
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("test", testService.findOne(id));
		return "addEditTest";
	}

	/**
	 * Removes the test object with the specified id.
	 * 
	 * @param id
	 *            the id of the test to remove
	 * @return the redirect view name, which redirects to test list
	 */
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remove(@PathVariable Long id) {
		testService.remove(id);
		return "redirect:..";
	}

	/**
	 * Persists the passed test object.
	 * 
	 * @param dto
	 *            the test to persist
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model object map
	 * @return the redirect view name, which redirects to test list
	 */
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String post(@Valid Test test, BindingResult bindingResult,
			Model model) {
		String viewName;
		if (!bindingResult.hasErrors()) {
			if (test.getId() != null) {
				Test t = testService.findOne(test.getId());
				test.setQuestions(t.getQuestions());
			}
			testService.save(test);
			viewName = "redirect:tests";
		} else {
			model.addAttribute("test", test);
			viewName = "addEditTest";
		}
		return viewName;
	}

	/**
	 * Cancels the new/edit test action.
	 * 
	 * @return the redirect view name, which redirects to test list
	 */
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:tests";
	}

	/**
	 * Returns the view for adding/removing questions to/from a test. Adds id of
	 * the test as model attribute <code>test</code>, list of all assigned
	 * questions to the test as model attribute <code>assignedQuestions</code>
	 * and list of all question not assigned to the test as
	 * <code>nonAssignedQuestions</code>.
	 * 
	 * @param id
	 *            the id of the test to add/remove questions
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/removing question to/from a test
	 */
	@RequestMapping(value = "/editQuestions/{id}", method = RequestMethod.GET)
	public String getAddRemoveQuestions(@PathVariable Long id, Model model) {
		Test test = testService.findOne(id);
		List<Question> nonAssignedQuestions = questionService.findAll();
		nonAssignedQuestions.removeAll(test.getQuestions());
		List<Question> assignedQuestions = new ArrayList<>();
		assignedQuestions.addAll(test.getQuestions());
		model.addAttribute("test", id);
		model.addAttribute("assignedQuestions", assignedQuestions);
		model.addAttribute("nonAssignedQuestions", nonAssignedQuestions);
		return "addRemoveQuestions";
	}

	/**
	 * Adds new question to test. Adds id of the test as model attribute
	 * <code>test</code>, list of all assigned questions to the test as model
	 * attribute <code>assignedQuestions</code> and list of all question not
	 * assigned to the test as <code>nonAssignedQuestions</code>.
	 * 
	 * @param testId
	 *            the id of the test
	 * @param questionId
	 *            the id of the question that is added
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/removing question to/from a test
	 */
	@RequestMapping(params = "addQuestion", method = RequestMethod.POST)
	public String addQuestion(
			@RequestParam Long testId,
			@RequestParam(value = "nonAssignedQuestions", required = false, defaultValue = "-1") Long questionId,
			Model model) {
		Question question = questionService.findOne(questionId);
		Test test = testService.findOne(testId);
		if (question != null) {
			test.addQuestion(question);
			testService.save(test);
		} else {
			model.addAttribute("addQuestionRequired",
					"test.addQuestion.required");
		}
		List<Question> nonAssignedQuestions = questionService.findAll();
		nonAssignedQuestions.removeAll(test.getQuestions());
		List<Question> assignedQuestions = new ArrayList<>();
		assignedQuestions.addAll(test.getQuestions());
		model.addAttribute("test", testId);
		model.addAttribute("assignedQuestions", assignedQuestions);
		model.addAttribute("nonAssignedQuestions", nonAssignedQuestions);
		return "addRemoveQuestions";
	}

	/**
	 * Removes question from the test. Adds id of the test as model attribute
	 * <code>test</code>, list of all assigned questions to the test as model
	 * attribute <code>assignedQuestions</code> and list of all question not
	 * assigned to the test as <code>nonAssignedQuestions</code>.
	 * 
	 * @param testId
	 *            the id of the test
	 * @param questionId
	 *            the id of the question that is removed
	 * @param bindingResult
	 *            the binding result
	 * @param model
	 *            the model object map
	 * @return the name of the view for adding/removing question to/from a test
	 */
	@RequestMapping(params = "removeQuestion", method = RequestMethod.POST)
	public String removeQuestion(
			@RequestParam Long testId,
			@RequestParam(value = "assignedQuestions", required = false, defaultValue = "-1") Long questionId,
			Model model) {
		Question question = questionService.findOne(questionId);
		Test test = testService.findOne(testId);
		if (question != null) {
			test.removeQuestion(question);
			testService.save(test);
		} else {
			model.addAttribute("removeQuestionRequired",
					"test.removeQuestion.required");
		}
		List<Question> nonAssignedQuestions = questionService.findAll();
		nonAssignedQuestions.removeAll(test.getQuestions());
		List<Question> assignedQuestions = new ArrayList<>();
		assignedQuestions.addAll(test.getQuestions());
		model.addAttribute("test", testId);
		model.addAttribute("assignedQuestions", assignedQuestions);
		model.addAttribute("nonAssignedQuestions", nonAssignedQuestions);
		return "addRemoveQuestions";
	}
}
