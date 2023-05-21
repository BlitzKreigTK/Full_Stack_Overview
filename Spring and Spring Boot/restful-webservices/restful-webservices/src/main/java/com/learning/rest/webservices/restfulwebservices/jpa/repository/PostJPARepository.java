package com.learning.rest.webservices.restfulwebservices.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.rest.webservices.restfulwebservices.model.Post;

public interface PostJPARepository extends JpaRepository<Post, Integer> {

}
