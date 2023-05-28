package com.learning.googleOauth;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

	@GetMapping("/")
	public String printUserDetails(Authentication auth) {
		System.out.println("User Details: " + auth.getDetails());
		System.out.println("User Prinicipal: " + auth.getPrincipal());
		return "Hello There!";
	}

}
