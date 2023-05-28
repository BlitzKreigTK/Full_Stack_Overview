package com.learning.security.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
public class TodosController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static List<Todo> listTodos = List.of(new Todo("Tulsi", "Learn AWS"), new Todo("Tulsi", "Learn Azure"));

	@GetMapping("/todos")
	public List<Todo> retrieveTodos() {
		return listTodos;
	}

	@GetMapping("/user/{user}/todos")
	// Check if user has role USER and authentication name
	@PreAuthorize("hasRole('USER') and #user == authentication.name")
	// Return response having userName = 'Tulsi'
	@PostAuthorize("returnObject.username = 'Tulsi'")
	// RolesAllowed to specific groups (JSR250)
	@RolesAllowed({ "USER", "ADMIN" })
	// Specific roles allowed (securityEnabled)
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
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
