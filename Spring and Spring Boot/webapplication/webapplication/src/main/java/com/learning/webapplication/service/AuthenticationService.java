package com.learning.webapplication.service;

import org.springframework.stereotype.Service;

//Same as @Component but is used to represent some business logic implementation
@Service
public class AuthenticationService {
	public boolean authenticatedUser(String name, String password) {
		boolean validUser = name.equalsIgnoreCase("TULSI");
		boolean validPassword = password.equals("tulsi");
		return validUser && validPassword;
	}
}
