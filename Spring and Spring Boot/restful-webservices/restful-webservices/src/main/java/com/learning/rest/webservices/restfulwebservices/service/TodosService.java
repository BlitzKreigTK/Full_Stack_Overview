package com.learning.rest.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.learning.rest.webservices.restfulwebservices.model.Todos;

import jakarta.validation.Valid;

@Service
public class TodosService {
	private static List<Todos> todos = new ArrayList<>();
	private static int todosCount = 0;
	// Static list is refreshed everytime application restarts
	// Created a hard coded list of todos
	static {
		todos.add(new Todos(++todosCount, "Tulsi", "Learn Spring Framework", LocalDate.now().plusDays(7), true));
		todos.add(new Todos(++todosCount, "Tulsi", "Learn Apache Camel", LocalDate.now().plusDays(7), true));
		todos.add(new Todos(++todosCount, "Tulsi", "Learn Angular js", LocalDate.now().plusDays(7), true));
		todos.add(new Todos(++todosCount, "Sachin", "Learn Spring Boot", LocalDate.now().plusDays(3), true));
		todos.add(new Todos(++todosCount, "Shubham", "Learn Docker", LocalDate.now().plusDays(4), true));
		todos.add(new Todos(++todosCount, "Satya", "Learn Kubernetes", LocalDate.now().plusDays(5), true));
	}

	// Finding the list of todos by logged in UserName
	public List<Todos> findByUserName(String name) {
		// Using predicate to return if user exists or not.
		Predicate<? super Todos> predicate = todos -> todos.getUsername().equalsIgnoreCase(name);
		return todos.stream().filter(predicate).toList();
	}

	// For adding new todos
	public Todos addNewTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todos todo = new Todos(++todosCount, username, description, targetDate, done);
		todos.add(todo);
		return todo;
	}

	public void deleteTodo(int id) {
		// Using predicate to return if Id exists in the bean or not.
		Predicate<? super Todos> predicate = todos -> todos.getId() == id;
		todos.removeIf(predicate);
	}

	public Todos showUpdateTodo(int id) {
		// Using predicate to return if Id exists in the bean or not.
		Predicate<? super Todos> predicate = todos -> todos.getId() == id;
		Todos todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todos todo) {
		deleteTodo(todo.getId());
		todos.add(todo);
	}

	// Finding the specific Todo by logged in UserName
	public Todos findByTodobyId(String name, int id) {
		// Using predicate to return if user exists or not.
		Predicate<? super Todos> predicateUser = todos -> todos.getUsername().equalsIgnoreCase(name);
		Predicate<? super Todos> predicateTodo = todos -> todos.getId() == id;
		return todos.stream().filter(predicateUser).filter(predicateTodo).findFirst().get();
	}
}