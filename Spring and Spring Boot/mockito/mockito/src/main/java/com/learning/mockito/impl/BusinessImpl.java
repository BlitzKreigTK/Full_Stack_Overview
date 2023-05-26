package com.learning.mockito.impl;

public class BusinessImpl {

	private DataService dataService;

	public BusinessImpl(DataService dataServiceStub) {
		super();
		this.dataService = dataServiceStub;
	}

	public int findGreatestFromAllData() {
		int greatestVal = Integer.MIN_VALUE;
		int[] data = dataService.retrieveAllData();

		for (int value : data) {
			if (value > greatestVal) {
				greatestVal = value;
			}
		}

		return greatestVal;
	}
}

interface DataService {
	int[] retrieveAllData();
}