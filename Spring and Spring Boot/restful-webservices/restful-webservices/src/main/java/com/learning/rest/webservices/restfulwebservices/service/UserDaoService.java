package com.learning.rest.webservices.restfulwebservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.learning.rest.webservices.restfulwebservices.model.User;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int userCount = 0;

	static {
		users.add(new User(++userCount, "Tulsi", LocalDate.now().minusYears(30)));
		users.add(new User(++userCount, "Raunit", LocalDate.now().minusYears(20)));
		users.add(new User(++userCount, "Satya", LocalDate.now().minusYears(15)));
	}

	// Return all users static as a list.
	public List<User> findAll() {
		return users;
	}

	// Return specific user by Id, match id passed by controller and filter the id
	// using predicate, fetch first matching id and return User object for that.
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	// Add new User details from RequestBody to the users list.
	public User save(User user) {
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	// Delete a user by id if found
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
