package com.learning.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.learning.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.learning.rest.webservices.restfulwebservices.model.User;
import com.learning.rest.webservices.restfulwebservices.service.UserDaoService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDao;

	// Get All Users as list
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDao.findAll();
	}

	// Get Users by Id as User object
	@GetMapping("/users/{id}")
	public EntityModel<User> findById(@PathVariable int id) {
		User findUser = userDao.findOne(id);
		if (findUser == null) {
			throw new UserNotFoundException("User Not Found with Id: " + id);
		}
		// To wrap your User class around EnitityModel to create link
		EntityModel<User> entityModel = EntityModel.of(findUser);
		// To create a link to /users or retrieveAllUsers()
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		// Adding the link created and specifying it with "all_users"
		entityModel.add(link.withRel("all_user"));
		return entityModel;
	}

	// Delete Users by Id
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable int id) {
		userDao.deleteById(id);
	}

	// Post request to add a new User
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		// Location of new User added at /users/{id}
		URI location = ServletUriComponentsBuilder // to create links based on the current HttpServletRequest
				.fromCurrentRequest() // from current url /users
				.path("/{id}") // append new id value at the current url
				.buildAndExpand(savedUser.getId()) // to get new users id added
				.toUri(); // create to a uri.
		return ResponseEntity.created(location).build();
	}
}
