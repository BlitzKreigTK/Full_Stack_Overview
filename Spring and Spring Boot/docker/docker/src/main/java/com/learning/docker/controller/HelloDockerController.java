package com.learning.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloDockerController {
	@GetMapping("/")
	public String helloDocker() {
		return "{\"message\": \"Hello welcome to learning docker using Spring boot V3!\"}";
	}
}
