package com.learning.security.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodosController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static List<Todo> listTodos = List.of(new Todo("Tulsi", "Learn AWS"), new Todo("Tulsi", "Learn Azure"));

	@GetMapping("/todos")
	public List<Todo> retrieveTodos() {
		return listTodos;
	}

	@GetMapping("/user/{user}/todos")
	public Todo getUserTodos(@PathVariable String user) {
		return listTodos.get(0);
	}

	@PostMapping("/user/{user}/todos")
	public void createTodo(@PathVariable String user, @RequestBody Todo todo) {
		// listTodos.add(todo);
		logger.info("Creating new Todo {} for {}", todo, user);
	}
}

record Todo(String username, String description) {
}
