package com.learning.mockito.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BusinessImplStubTest {

	@Test
	void findGreatestFromAllDataWithEleTest() {
		DataService dataServiceStub1 = new DataServiceStub1();
		BusinessImpl business = new BusinessImpl(dataServiceStub1);
		int result = business.findGreatestFromAllData();
		assertEquals(25, result);
	}

	@Test
	void findGreatestFromAllDataWithOneEleTest() {
		DataService dataServiceStub2 = new DataServiceStub2();
		BusinessImpl business = new BusinessImpl(dataServiceStub2);
		int result = business.findGreatestFromAllData();
		assertEquals(35, result);
	}
}

// A stub is a fake class that comes with preprogrammed return values. 
class DataServiceStub1 implements DataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 10, 15, 25 };
	}
}

class DataServiceStub2 implements DataService {
	@Override
	public int[] retrieveAllData() {
		return new int[] { 35 };
	}
}
