package com.junit;

public class CalculateSum {
	public static int calc_sum_of_ele(int[] numbers) {
		int sum = 0;
		for (int num : numbers) {
			sum += num;
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		System.out.println("Sum: " + calc_sum_of_ele(arr));
	}
}
