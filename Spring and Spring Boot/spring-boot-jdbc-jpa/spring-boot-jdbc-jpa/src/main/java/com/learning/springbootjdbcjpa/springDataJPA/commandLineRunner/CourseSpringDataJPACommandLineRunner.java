package com.learning.springbootjdbcjpa.springDataJPA.commandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.learning.springbootjdbcjpa.jpa.entity.Course;
import com.learning.springbootjdbcjpa.springDataJPA.repo.CourseSpringDataJPARepository;

@Component
public class CourseSpringDataJPACommandLineRunner implements CommandLineRunner {

	@Autowired
	private CourseSpringDataJPARepository springDataJpaRepository;

	@Override
	public void run(String... args) throws Exception {
		// Insert Record
		springDataJpaRepository.save(new Course(1, "Tulsi", "Learn Spring Data JPA"));
		springDataJpaRepository.save(new Course(2, "Sachin", "Learn Spring Boot"));
		springDataJpaRepository.save(new Course(3, "Tarun", "Learn Spring MVC"));
		springDataJpaRepository.save(new Course(4, "Satya", "Learn Selenium"));
		// Delete Record
		springDataJpaRepository.deleteById(2l);
		// Get Record
		System.out.println(springDataJpaRepository.findById(1l));
		// Get Record by Author, Name
		System.out.println("Author ---> " + springDataJpaRepository.findByAuthor("Learn Spring Data JPA"));
		System.out.println("Name ---> " + springDataJpaRepository.findByName("Satya"));
		// Get All record
		System.out.println("All Record ---> " + springDataJpaRepository.findAll());
		System.out.println("Count ---> " + springDataJpaRepository.count());
	}
}
