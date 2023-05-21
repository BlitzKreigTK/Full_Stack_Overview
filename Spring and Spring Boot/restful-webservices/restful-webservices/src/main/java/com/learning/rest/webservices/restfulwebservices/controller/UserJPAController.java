package com.learning.rest.webservices.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.learning.rest.webservices.restfulwebservices.jpa.repository.PostJPARepository;
import com.learning.rest.webservices.restfulwebservices.jpa.repository.UserJPARepository;
import com.learning.rest.webservices.restfulwebservices.model.Post;
import com.learning.rest.webservices.restfulwebservices.model.User;

import jakarta.validation.Valid;

@RestController
public class UserJPAController {

	@Autowired
	private UserJPARepository userJPARepo;

	@Autowired
	private PostJPARepository postJPARepo;

	// Get All Users as list
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userJPARepo.findAll();
	}

	// Get Users by Id as User object
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> findById(@PathVariable int id) {
		Optional<User> findUser = userJPARepo.findById(id);
		if (findUser.isEmpty()) {
			throw new UserNotFoundException("User Not Found with Id: " + id);
		}
		// To wrap your User class around EnitityModel to create link
		EntityModel<User> entityModel = EntityModel.of(findUser.get());
		// To create a link to /users or retrieveAllUsers()
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		// Adding the link created and specifying it with "all_users"
		entityModel.add(link.withRel("all_user"));
		return entityModel;
	}

	// Delete Users by Id
	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable int id) {
		userJPARepo.deleteById(id);
	}

	// Post request to add a new User
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userJPARepo.save(user);
		// Location of new User added at /users/{id}
		URI location = ServletUriComponentsBuilder // to create links based on the current HttpServletRequest
				.fromCurrentRequest() // from current url /users
				.path("/{id}") // append new id value at the current url
				.buildAndExpand(savedUser.getId()) // to get new users id added
				.toUri(); // create to a uri.
		return ResponseEntity.created(location).build();
	}

	// Get list of posts for a specific user id
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsUser(@PathVariable int id) {
		Optional<User> userId = userJPARepo.findById(id);
		if (userId.isEmpty()) {
			throw new UserNotFoundException("User Not Found with Id: " + id);
		}
		List<Post> listPost = userId.get().getPost();
		return listPost;
	}

	// Create new post for a specific user id
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostUser(@PathVariable int id, @Valid @RequestBody Post post) {
		Optional<User> user = userJPARepo.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("User Not Found with Id: " + id);
		}
		post.setUser(user.get());
		Post savedPost = postJPARepo.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
