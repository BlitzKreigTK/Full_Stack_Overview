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
import org.springframework.web.bind.annotation.RestController;

import com.learning.rest.webservices.restfulwebservices.jpa.repository.TodosJPARepository;
import com.learning.rest.webservices.restfulwebservices.model.Todos;

@RestController
public class TodosJPAController {

	@Autowired
	private TodosJPARepository todosJPARepo;

	// Get list of Todos by User
	@GetMapping("/users/{user}/todos")
	public List<Todos> retrieveListOfTodos(@PathVariable String user) {
		return todosJPARepo.findByusername(user);
	}

	// Get list of Todos for specific user id
	@GetMapping("/users/{user}/todos/{id}")
	public Todos retrieveTodoById(@PathVariable String user, @PathVariable int id) {
		return todosJPARepo.findById(id).get();
	}

	// Delete Todos for specific user
	@DeleteMapping("/users/{user}/todos/{id}")
	public ResponseEntity<Void> deleteTodoById(@PathVariable String user, @PathVariable int id) {
		todosJPARepo.deleteById(id);
		// Create a builder with a NO_CONTENT HTTP status.
		return ResponseEntity.noContent().build();
	}

	// Update Todo for specific user and Todo
	@PutMapping("/users/{user}/todos/{id}")
	public Todos updateTodoById(@PathVariable String user, @PathVariable int id, @RequestBody Todos todo) {
		todosJPARepo.save(todo);
		return todo;
	}

	// Create new Todo
	@PostMapping("/users/{user}/todos")
	public Todos createNewTodo(@PathVariable String user, @RequestBody Todos todo) {
		todo.setUsername(user);
		todo.setId(null);
		return todosJPARepo.save(todo);
	}
}
