package com.learning.rest.webservices.restfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.rest.webservices.restfulwebservices.model.Todos;
import com.learning.rest.webservices.restfulwebservices.service.TodosService;

//@RestController
public class TodosController {

	@Autowired
	private TodosService todosService;

	// Get list of Todos by User
	@GetMapping("/users/{user}/todos")
	public List<Todos> retrieveListOfTodos(@PathVariable String user) {
		return todosService.findByUserName(user);
	}

	// Get list of Todos for specific user id
	@GetMapping("/users/{user}/todos/{id}")
	public Todos retrieveTodoById(@PathVariable String user, @PathVariable int id) {
		return todosService.findByTodobyId(user, id);
	}

	// Delete Todos for specific user
	@DeleteMapping("/users/{user}/todos/{id}")
	public ResponseEntity<Void> deleteTodoById(@PathVariable String user, @PathVariable int id) {
		todosService.deleteTodo(id);
		// Create a builder with a NO_CONTENT HTTP status.
		return ResponseEntity.noContent().build();
	}

	// Update Todo for specific user and Todo
	@PutMapping("/users/{user}/todos/{id}")
	public Todos updateTodoById(@PathVariable String user, @PathVariable int id, @RequestBody Todos todo) {
		todosService.updateTodo(todo);
		return todo;
	}

	// Create new Todo
	@PostMapping("/users/{user}/todos")
	public Todos createNewTodo(@PathVariable String user, @RequestBody Todos todo) {
		Todos newTodo = todosService.addNewTodo(user, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return newTodo;
	}
}
