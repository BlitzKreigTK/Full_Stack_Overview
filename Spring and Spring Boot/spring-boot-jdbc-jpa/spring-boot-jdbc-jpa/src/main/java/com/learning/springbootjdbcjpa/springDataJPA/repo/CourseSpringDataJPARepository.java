package com.learning.springbootjdbcjpa.springDataJPA.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.springbootjdbcjpa.jpa.entity.Course;

public interface CourseSpringDataJPARepository extends JpaRepository<Course, Long> {
	// Custom methods
	List<Course> findByAuthor(String Author);

	List<Course> findByName(String Name);
}
