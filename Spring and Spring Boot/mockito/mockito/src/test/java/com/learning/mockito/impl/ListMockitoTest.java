package com.learning.mockito.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ListMockitoTest {
	@SuppressWarnings("rawtypes")
	@Mock
	private List list;

	@Test
	void parameterizedTest() {
		when(list.get(0)).thenReturn("True");
		assertEquals("True", list.get(0));
	}

	@Test
	void nonParameterizedTest() {
		when(list.size()).thenReturn(3);
		assertEquals(3, list.size());
	}

	@Test
	void multipleParameterizedTest() {
		when(list.size()).thenReturn(3).thenReturn(2); // Will return 3 and then 2
		assertEquals(3, list.size()); // First value will be 3
		assertEquals(2, list.size());
		assertEquals(2, list.size()); // Final value will 2 irrespective of times called
	}

	@Test
	void anyTypeParameterTest() {
		// Mockito.any --> Can be used for any values 
		when(list.get(Mockito.anyInt())).thenReturn(3).thenReturn(2);
		assertEquals(3, list.get(3));
		assertEquals(2, list.get(2));
		assertEquals(2, list.get(10));
	}
}
