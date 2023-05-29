package com.learning.springAOP.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springAOP.annotations.TrackTime;
import com.learning.springAOP.repository.Dataservice_1_Repository;

@Service
public class Business_1_Service {
	@Autowired
	private Dataservice_1_Repository data1repo;

	@TrackTime
	public int findMax() {
		int[] data = data1repo.retrieveData();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// throw new RuntimeException("Something went wrong!");
		return Arrays.stream(data).max().orElse(0);
	}
}
