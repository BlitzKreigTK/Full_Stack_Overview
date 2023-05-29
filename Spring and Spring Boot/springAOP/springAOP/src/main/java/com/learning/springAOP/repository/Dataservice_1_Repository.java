package com.learning.springAOP.repository;

import org.springframework.stereotype.Repository;

@Repository
public class Dataservice_1_Repository {

	public int[] retrieveData() {

		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new int[] { 11, 22, 33, 65, 75 };
	}
}
