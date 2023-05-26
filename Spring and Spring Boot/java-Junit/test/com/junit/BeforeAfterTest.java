package com.junit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BeforeAfterTest {
	@BeforeAll
	static void beforeAllTest() {
		System.out.println("========Before All========");
	}

	@BeforeEach
	void beforeEachTest() {
		System.out.println("====Before Each====");
	}

	@Test
	void test1() {
		System.out.println("Test1");
	}

	@Test
	void test2() {
		System.out.println("Test2");
	}

	@AfterEach
	void afterEachTest() {
		System.out.println("====After Each====");
	}

	@AfterAll
	static void afterAllTest() {
		System.out.println("========After All========");
	}
}
