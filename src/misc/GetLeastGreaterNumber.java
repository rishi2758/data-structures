package misc;

import java.util.ArrayList;
import java.util.Arrays;

public class GetLeastGreaterNumber {

	public int getLeastGreaterNumber(ArrayList<Integer> nums, int target) {
		// 2,3,4,6,9 t: 5

		int low = 0;
		int high = nums.size() - 1;

		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (nums.get(mid) < target) {
				low = mid + 1;
			} else {
				if (nums.get(mid) > target) {
					if (mid == 0 || nums.get(mid - 1) <= target) {
						return mid;
					} else {
						high = mid - 1;
					}
				} else {
					low = mid + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {

		ArrayList<Integer> nums = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 5, 6, 7));
		GetLeastGreaterNumber slgn = new GetLeastGreaterNumber();
		int idx = slgn.getLeastGreaterNumber(nums, -1);
		if (idx != -1) {
			System.out.println("found " + nums.get(idx));
		}

	}

}
