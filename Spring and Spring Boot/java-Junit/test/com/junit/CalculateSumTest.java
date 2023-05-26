package com.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CalculateSumTest {

	@Test
	void calcSumWithEle() {
		assertEquals(6, CalculateSum.calc_sum_of_ele(new int[] { 1, 2, 3 }));
		// fail("Not yet implemented");
	}

	@Test
	void calcSumWithNoEle() {
		assertEquals(0, CalculateSum.calc_sum_of_ele(new int[] {}));
	}
}
