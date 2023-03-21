package misc;

import java.util.LinkedList;
import java.util.Queue;

public class FirstNegative {

	/**
	 * { 12, -1, -7, 8, -15, 30, 16, 28 }; 3 12 -1 -7
	 */

	public Integer[] firstNegative(int[] nums, int k) {

		if (nums == null || nums.length == 0 || k <= 0) {
			return null;
		}

		Queue<Integer> negatives = new LinkedList<>();
		for (int i = 0, j = 0; i < nums.length && j < nums.length;) {
			if (j - i + 1 <= k) {
				if (nums[j] < 0) {
					negatives.add(nums[j]);
				}
			} else if (j - i + 1 == k) {
				int res = negatives.isEmpty() ? 0 : negatives.peek();
				if (nums[i] < 0 && !negatives.isEmpty()) {
					negatives.poll();
				}
				System.out.print(res);
				++i;
			}
			++j;
		}

		return negatives.toArray(new Integer[0]);
	}

	public static void main(String[] args) {
		int[] nums = { 12, -1, -7, 8, -15, 30, 16, 28 };
		Integer[] res = new FirstNegative().firstNegative(nums, 3);
		for (Integer i : res) {
			System.out.print(i + ",");
		}
	}

}
