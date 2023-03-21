package misc;

import java.util.HashSet;

public class LCS {

	private int earn(int[] nums, int current) {

		if (current >= nums.length) {
			return 0;
		}

		int select = nums[current];
		for (int i = 0; i < nums.length; i++) {
			if (current != i) {
				if (select + 1 != nums[i] && nums[i] != select - 1) {
					select = select + earn(nums, i);
				}
			}
		}

		return Math.max(earn(nums, current + 1), select);
	}

	/**
	 * Question: Given an array of size n with unique positive integers and a
	 * positive integer K, check if there exists a combination of elements in the
	 * array satisfying below constraints. a. The sum of all such elements is K b.
	 * None of those elements are adjacent in the original array
	 * <p>
	 * Example: Input: array = {1, 9, 8, 3, 6, 7, 5, 11, 12, 4} K = 14 Output: [3,
	 * 7, 4]
	 **/

	public boolean existsCombination(int[] nums, int k) {

		if (nums.length == 0 || k < 0) {
			return false;
		}
		_solve(nums, k, 0, new HashSet<>());
		return true;

	}

	private boolean _solve(int[] nums, int k, int curr, HashSet<Integer> set) {
		if (k == 0) {
			return true;
		}

		if (k < 0) {
			return false;
		}

		for (int i = curr + 2; i < nums.length; i++) {
			int remaining = k - nums[i];
			boolean solvable = _solve(nums, remaining, i, set);
			if (solvable) {
				set.add(nums[i]);
			}
		}

		return false;

	}

	public static void main(String[] args) {

		System.out.println("--Running Main--");
		int[] nums = { 2, 3, 4 };
		int e = new LCS().earn(nums, 0);
		System.out.println(e);

	}
}