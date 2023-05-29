package com.learning.springAOP.repository;

import org.springframework.stereotype.Repository;

@Repository
public class Dataservice_2_Repository {

	public int[] retrieveData() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new int[] { 111, 222, 333, 665, 375 };
	}
}
