package com.learning.springAOP;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.learning.springAOP.service.Business_1_Service;
import com.learning.springAOP.service.Business_2_Service;

@SpringBootApplication
public class SpringAopApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Business_1_Service business1service;

	@Autowired
	private Business_2_Service business2service;

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("business1service : Max is {} ", business1service.findMax());
		logger.info("business2service : Min is {} ", business2service.findMin());
	}
}
