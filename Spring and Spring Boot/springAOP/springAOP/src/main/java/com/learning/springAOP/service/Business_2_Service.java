package com.learning.springAOP.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springAOP.annotations.TrackTime;
import com.learning.springAOP.repository.Dataservice_2_Repository;

@Service
public class Business_2_Service {
	@Autowired
	private Dataservice_2_Repository data2repo;

	@TrackTime
	public int findMin() {
		int[] data = data2repo.retrieveData();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// throw new RuntimeException("Something went wrong!");
		return Arrays.stream(data).min().orElse(0);
	}
}
