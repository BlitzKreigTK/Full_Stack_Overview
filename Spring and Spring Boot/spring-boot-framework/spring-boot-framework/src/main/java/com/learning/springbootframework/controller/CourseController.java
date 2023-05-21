package com.learning.springbootframework.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springbootframework.bean.Course;

@RestController
public class CourseController {

	@RequestMapping("/courses")
	public List<Course> retrieveAllCourses() {
		return Arrays.asList(new Course(1, "Tulsi", "Learn DevOps"), new Course(2, "Raunit", "Learn Spring Boot"),
				new Course(3, "Sachin", "Learn Camel"));
	}
}
