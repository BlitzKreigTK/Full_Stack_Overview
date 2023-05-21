package com.learning.webapplication.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.learning.webapplication.model.Todos;
import com.learning.webapplication.service.TodosService;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodosController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private TodosService todosService;

	// Showing the static list of todos by logged in username
	@RequestMapping("/listTodos")
	public String listAllTodos(String name, ModelMap model) {
		logger.info("Todo List Controller: listAllTodos()");
		String username = getLoggedInUserName();
		List<Todos> todo = todosService.findByUserName(username);
		model.put("todo", todo);
		return "ListTodos";
	}

	// To add new todo in todos list
	@RequestMapping(value = "/addNewTodo", method = RequestMethod.GET)
	public String showNewTodo(ModelMap model) {
		logger.info("Todo List Controller: showNewTodo()");
		String username = getLoggedInUserName();
		// GET method is not mapped to any Model todos we get error, so we need to map
		// to a default object to GET Request this is called 2 way binding 1 for GET and
		// 1 for POST
		Todos todo = new Todos(0, username, "Default Description", LocalDate.now().plusDays(1), true);
		model.put("todo", todo);
		return "AddTodo";
	}

	// To add new to do once submitted from /addNewTodo
	@RequestMapping(value = "/addNewTodo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid @ModelAttribute("todo") Todos todo, BindingResult result) {
		logger.info("Todo List Controller: addTodo()");
		if (result.hasErrors()) {
			return "AddTodo";
		}
		String username = getLoggedInUserName();
		todosService.addNewTodo(username, todo.getDescription(), todo.getTargetDate(), true);
		return "redirect:listTodos";
	}

	// To delete a todo
	@RequestMapping("/del-Todos")
	public String deleteTodos(@RequestParam int id) {
		logger.info("Todo List Controller: deleteTodos()");
		todosService.deleteTodo(id);
		return "redirect:listTodos";
	}

	// To show a update a todo
	@RequestMapping(value = "/update-Todos", method = RequestMethod.GET)
	public String showUpdateTodo(@RequestParam int id, ModelMap model) {
		logger.info("Todo List Controller: updateTodo()");
		Todos todo = todosService.showUpdateTodo(id);
		model.addAttribute("todo", todo);
		return "AddTodo";
	}

	// To update a todo
	@RequestMapping(value = "/update-Todos", method = RequestMethod.POST)
	public String updateTodo(@RequestParam int id, @Valid @ModelAttribute("todo") Todos todo, BindingResult result,
			ModelMap model) {
		logger.info("Todo List Controller: updateTodo()");
		if (result.hasErrors()) {
			return "AddTodo";
		}
		String username = getLoggedInUserName();
		todo.setUsername(username);
		todosService.updateTodo(todo);
		return "redirect:listTodos";
	}

	// Method to return the current user name that is logged in
	public String getLoggedInUserName() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}
