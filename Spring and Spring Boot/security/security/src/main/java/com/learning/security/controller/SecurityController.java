package com.learning.security.controller;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SecurityController {

	@GetMapping("/getCsrf")
	public CsrfToken retrieveCsrfToken(HttpServletRequest http) {
		// Get csrf token for the servlet requests incoming
		return (CsrfToken) http.getAttribute("_csrf");
	}
}
