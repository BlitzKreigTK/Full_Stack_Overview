package com.learning.mockito.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BusinessImplMockTest {

	@Mock
	private DataService dataService;

	@InjectMocks
	private BusinessImpl businessImpl;

	@Test
	void findGreatestFromAllDataWithEleTest() {
		when(dataService.retrieveAllData()).thenReturn(new int[] { 15, 25, 20 });
		int result = businessImpl.findGreatestFromAllData();
		assertEquals(25, result);
	}

	@Test
	void findGreatestFromAllDataWithOneEleTest() {
		when(dataService.retrieveAllData()).thenReturn(new int[] { 35 });
		int result = businessImpl.findGreatestFromAllData();
		assertEquals(35, result);
	}

	@Test
	void emptyArrayEleTest() {
		when(dataService.retrieveAllData()).thenReturn(new int[] {});
		int result = businessImpl.findGreatestFromAllData();
		assertEquals(Integer.MIN_VALUE, result);
	}
}
