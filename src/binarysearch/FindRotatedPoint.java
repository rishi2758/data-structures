package binarysearch;

import java.util.Arrays;

public class FindRotatedPoint {

	public int findRotatedPivot(int[] nums) {

		if (nums == null || nums.length == 0) {
			throw new RuntimeException("invalid arguments");
		}

		int low = 0;
		int high = nums.length - 1;
		while (low < high && nums[low] > nums[high]) {
			int mid = low + (high - low) / 2;
			if (mid - 1 >= 0 && nums[mid - 1] >= nums[mid]) {
				return mid;
			} else if (nums[low] <= nums[mid]) {
				low = mid+1;
			} else {
				high = mid-1;
			}

		}
		
		return low;
	}
	
	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 1, 2, 3 };
		int[] nums1 = { 4, 4, 5, 6, 7, 7, 1, 2, 2, 3 };
		System.out.println(new FindRotatedPoint().findRotatedPivot(nums));
		System.out.println(new FindRotatedPoint().findRotatedPivot(nums1));
		Arrays.sort(nums);
		System.out.println(new FindRotatedPoint().findRotatedPivot(nums));
		Arrays.sort(nums1);
		System.out.println(new FindRotatedPoint().findRotatedPivot(nums1));
	}

}
