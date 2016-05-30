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
import rs.code9.taster.web.dto.TestDTO;

/**
 * Controller for CRUD operations on tests.
 * 
 * @author p.stanic
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
	 * Retrieves all tests and returns them as model attribute <code>tests</code>.
	 * 
	 * @return list of all tests, as model attribute <code>tests</code>
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ModelAttribute("tests")
	public List<TestDTO> get() {
		List<TestDTO> retVal = new ArrayList<>();
		List<Test> tests = testService.findAll();
		TestDTO dto = new TestDTO();
		for (Test test : tests) {
			dto = new TestDTO();
			dto.setId(test.getId());
			dto.setName(test.getName());
			dto.setCreateDate(test.getCreateDate());
			dto.setCreatedBy(test.getCreatedBy());
			retVal.add(dto);
		}
		return retVal;
	}

	/**
	 * Returns the view for adding new test. Adds empty test DTO as model attribute <code>test</code>.
	 * 
	 * @param model the model object map
	 * @return the name of the view for adding/editing a test
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		TestDTO dto = new TestDTO();
		dto.setCreateDate(new Date());
		model.addAttribute("testDTO", dto);
		return "addEditTest";
	}

	/**
	 * Returns the view for editing a test. Adds test DTO, found by passed id, as model attribute <code>test</code>.
	 * 
	 * @param id the id of the test to edit
	 * @param model the model object map
	 * @return the name of the view for adding/editing a test
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		Test test = testService.getOne(id);
		TestDTO dto = new TestDTO();
		dto.setId(test.getId());
		dto.setName(test.getName());
		dto.setCreateDate(test.getCreateDate());
		dto.setCreatedBy(test.getCreatedBy());
		model.addAttribute("testDTO", dto);
		return "addEditTest";
	}

	/**
	 * Removes the test object with the specified id.
	 * 
	 * @param id the id of the test to remove
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
	 * @param dto the test to persist
	 * @param bindingResult the binding result
	 * @param model the model object map
	 * @return the redirect view name, which redirects to test list
	 */
	@RequestMapping(params = "save", method = RequestMethod.POST)
	public String post(@Valid TestDTO dto, BindingResult bindingResult, Model model) {
		String viewName;
		if (!bindingResult.hasErrors()) {
			Test test;
			if (dto.getId() != null) {
				test = testService.getOne(dto.getId());
			} else {
				test = new Test();
			}
			test.setName(dto.getName());
			// not needed, as create date is read only
			// test.setCreateDate(dto.getCreateDate());
			test.setCreatedBy(dto.getCreatedBy());
			testService.save(test);
			viewName = "redirect:tests";
		} else {
			model.addAttribute("testDTO", dto);
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
	 * Returns the view for adding/removing questions to/from a test. Adds id of the test as model attribute
	 * <code>test</code>, list of all assigned questions to the test as model attribute <code>assignedQuestions</code>
	 * and list of all question not assigned to the test as <code>nonAssignedQuestions</code>.
	 * 
	 * @param id the id of the test to add/remove questions
	 * @param model the model object map
	 * @return the name of the view for adding/removing question to/from a test
	 */
	@RequestMapping(value = "/editQuestions/{id}", method = RequestMethod.GET)
	public String getAddRemoveQuestions(@PathVariable Long id, Model model) {
		Test test = testService.getOne(id);
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
	 * Adds new question to test. Adds id of the test as model attribute <code>test</code>, list of all assigned
	 * questions to the test as model attribute <code>assignedQuestions</code> and list of all question not assigned to
	 * the test as <code>nonAssignedQuestions</code>.
	 * 
	 * @param testId the id of the test
	 * @param questionId the id of the question that is added
	 * @param bindingResult the binding result
	 * @param model the model object map
	 * @return the name of the view for adding/removing question to/from a test
	 */
	@RequestMapping(params = "addQuestion", method = RequestMethod.POST)
	public String addQuestion(
			@RequestParam Long testId,
			@RequestParam(value = "nonAssignedQuestions", required = false, defaultValue = "-1") Long questionId,
			Model model) {
		Question question = questionService.getOne(questionId);
		Test test = testService.getOne(testId);
		if (question != null) {
			testService.addQuestion(test, question);
			testService.save(test);
		} else {
			model.addAttribute("addQuestionRequired", "test.addQuestion.required");
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
	 * Removes question from the test. Adds id of the test as model attribute <code>test</code>, list of all assigned
	 * questions to the test as model attribute <code>assignedQuestions</code> and list of all question not assigned to
	 * the test as <code>nonAssignedQuestions</code>.
	 * 
	 * @param testId the id of the test
	 * @param questionId the id of the question that is removed
	 * @param bindingResult the binding result
	 * @param model the model object map
	 * @return the name of the view for adding/removing question to/from a test
	 */
	@RequestMapping(params = "removeQuestion", method = RequestMethod.POST)
	public String removeQuestion(
			@RequestParam Long testId,
			@RequestParam(value = "assignedQuestions", required = false, defaultValue = "-1") Long questionId,
			Model model) {
		Question question = questionService.getOne(questionId);
		Test test = testService.getOne(testId);
		if (question != null) {
			testService.removeQuestion(test, question);
			testService.save(test);
		} else {
			model.addAttribute("removeQuestionRequired", "test.removeQuestion.required");
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
