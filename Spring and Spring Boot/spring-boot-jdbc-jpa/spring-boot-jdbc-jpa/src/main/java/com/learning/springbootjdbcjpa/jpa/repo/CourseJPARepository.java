package com.learning.springbootjdbcjpa.jpa.repo;

import org.springframework.stereotype.Repository;

import com.learning.springbootjdbcjpa.jpa.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

// Added to do transaction with JPA
@Repository
@Transactional
public class CourseJPARepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void insertNewRec(Course course) {
		entityManager.merge(course);
	}

	public Course getRec(long id) {
		return entityManager.find(Course.class, id);
	}

	public void deleteRec(long id) {
		Course course = entityManager.find(Course.class, id);
		entityManager.remove(course);
	}

}
