package com.learning.webapplication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomePageController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	// Once login url hit return Welcome.jsp
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String goToWlcmPage(ModelMap model) {
		logger.info("Welcome Page controller: goToWlcmPage()");
		model.put("name", getLoggedInUserName());
		return "Welcome";
	}

	public String getLoggedInUserName() {
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}
}