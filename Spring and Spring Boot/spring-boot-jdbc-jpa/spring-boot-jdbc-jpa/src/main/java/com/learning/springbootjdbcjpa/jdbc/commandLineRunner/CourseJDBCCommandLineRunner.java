package com.learning.springbootjdbcjpa.jdbc.commandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.springbootjdbcjpa.jdbc.beans.Course;
import com.learning.springbootjdbcjpa.jdbc.repo.CourseJDBCRepository;

@Component
public class CourseJDBCCommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseJDBCRepository jdbcRepository;

	@Override
	public void run(String... args) throws Exception {
		// Insert Record
		jdbcRepository.insertNewRec(new Course(1, "Tulsi", "Learn Spring JDBC"));
		jdbcRepository.insertNewRec(new Course(2, "Sachin", "Learn Spring Boot"));
		jdbcRepository.insertNewRec(new Course(3, "Tarun", "Learn Spring MVC"));
		jdbcRepository.insertNewRec(new Course(4, "Satya", "Learn Selenium"));

		// Delete Record
		jdbcRepository.deleteRec(2);

		// Get Record
		System.out.println(jdbcRepository.getRec(1));
		System.out.println(jdbcRepository.getRec(3));

	}
}
