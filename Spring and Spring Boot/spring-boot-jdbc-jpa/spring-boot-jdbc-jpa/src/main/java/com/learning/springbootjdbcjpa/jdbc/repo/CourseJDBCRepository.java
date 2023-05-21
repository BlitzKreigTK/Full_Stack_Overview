package com.learning.springbootjdbcjpa.jdbc.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.learning.springbootjdbcjpa.jdbc.beans.Course;

@Repository
public class CourseJDBCRepository {
	@Autowired
	private JdbcTemplate springjdbcTemplate;

	private static String INSERT_COURSE = "INSERT INTO COURSES (ID, NAME, AUTHOR) VALUES (?, ?, ?)";

	private static String DELETE_COURSE = "DELETE FROM COURSES WHERE ID = ?";

	private static String GET_COURSE = "SELECT * FROM COURSES WHERE ID = ?";

	public void insertNewRec(Course course) {
		springjdbcTemplate.update(INSERT_COURSE, course.getId(), course.getName(), course.getAuthor());
	}

	public void deleteRec(long id) {
		springjdbcTemplate.update(DELETE_COURSE, id);
	}

	public Course getRec(long id) {
		// Repository will query Database and returned resultset needs to be mapped a/c
		// to Course Bean so we use new BeanPropertyRowMapper<>(<Bean>.class)
		return springjdbcTemplate.queryForObject(GET_COURSE, new BeanPropertyRowMapper<>(Course.class), id);
	}
}
