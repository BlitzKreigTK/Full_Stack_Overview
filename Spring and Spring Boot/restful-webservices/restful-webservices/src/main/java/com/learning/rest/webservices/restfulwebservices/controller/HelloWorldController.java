package com.learning.rest.webservices.restfulwebservices.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.learning.rest.webservices.restfulwebservices.model.HelloWorld;

@RestController
public class HelloWorldController {

	@Autowired
	private MessageSource messageSource;

	// @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello Rest API World!!";
	}

	@GetMapping(path = "/basicAuth")
	public String basicAuth() {
		return "Success";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorld helloWorldBean() {
		return new HelloWorld("Hello Rest API World from Bean!!");
	}

	@GetMapping(path = "/hello/path-variable/{name}")
	public HelloWorld helloWorldPathVarBean(@PathVariable String name) {
		return new HelloWorld(String.format("Hello! %s", name));
	}

	@GetMapping(path = "/hello/internalization")
	public String helloInternalization() {
		// To get Accept header for language
		Locale locale = LocaleContextHolder.getLocale();
		// "<properties_name>","<replacing_value>","<Default_Msg>","<locale>"
		return messageSource.getMessage("good.morning.message", null, "Default message", locale);
	}
}
