package com.learning.springbootjdbcjpa.jpa.commandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.springbootjdbcjpa.jpa.entity.Course;
import com.learning.springbootjdbcjpa.jpa.repo.CourseJPARepository;

@Component
public class CourseJPACommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJPARepository jpaRepository;

	@Override
	public void run(String... args) throws Exception {
		// Insert Record
		jpaRepository.insertNewRec(new Course(1, "Tulsi", "Learn Spring JPA"));
		jpaRepository.insertNewRec(new Course(2, "Sachin", "Learn Spring Boot"));
		jpaRepository.insertNewRec(new Course(3, "Tarun", "Learn Spring MVC"));
		jpaRepository.insertNewRec(new Course(4, "Satya", "Learn Selenium"));

		// Delete Record
		jpaRepository.deleteRec(2);

		// Get Record
		System.out.println(jpaRepository.getRec(1));
		System.out.println(jpaRepository.getRec(3));

	}
}
