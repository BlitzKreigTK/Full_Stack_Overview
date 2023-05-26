package com.learning.rest.webservices.restfulwebservices.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.rest.webservices.restfulwebservices.model.Todos;

public interface TodosJPARepository extends JpaRepository<Todos, Integer> {

	List<Todos> findByusername(String user);

}
