package com.learning.webapplication.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.webapplication.model.Todos;

public interface TodosJPARepository extends JpaRepository<Todos, Integer> {
	// findBy<Username> --> Same as defined in Todos <username>
	public List<Todos> findByUsername(String username);
}
