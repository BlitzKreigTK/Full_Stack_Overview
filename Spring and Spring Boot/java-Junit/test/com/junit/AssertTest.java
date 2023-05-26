package com.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class AssertTest {
	List<String> courses = Arrays.asList("AWS", "Kubernetes", "Docker");
	List<String> courses2 = Arrays.asList("AWS", "Kubernetes", "Docker");

	@Test
	void assertMethodTest() {
		boolean test = true;
		boolean test2 = false;
		// assertEquals has almost all data type checks
		assertEquals(test, courses.contains("AWS")); // Boolean check
		assertEquals("Test1", "Test1"); // String check
		assertEquals(courses, courses2, "Comparing list"); // List check
		assertNotNull(courses); // checks if it is not null
		assertNull(null); // checks if it is null
		assertTrue("Test is true", test); // checks if it is true
		assertFalse(test2); // checks if it is false
	}

}
