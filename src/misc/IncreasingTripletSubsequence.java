package misc;

import java.util.Stack;

public class IncreasingTripletSubsequence {

	public int[] getNextGreaterToRight(int[] nums) {

		if (nums == null || nums.length == 0) {
			return nums;
		}

		int[] nge = new int[nums.length];
		nge[nums.length - 1] = -1;

		Stack<Integer> stack = new Stack<>();
		stack.push(nums.length - 1);
		for (int i = nums.length - 2; i >= 0; i--) {
			while (!stack.isEmpty() && nums[i] >= nums[stack.peek()]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				nge[i] = -1;
			} else {
				nge[i] = stack.peek();
			}
			stack.push(i);
		}
		return nge;
	}

	public int[] getNextSmallestToLeft(int[] nums) {

		if (nums == null || nums.length == 0) {
			return nums;
		}

		int[] nsl = new int[nums.length];
		nsl[0] = -1;

		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (int i = 1; i < nums.length; i++) {
			while (!stack.isEmpty() && nums[i] <= nums[stack.peek()]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				nsl[i] = -1;
			} else {
				nsl[i] = stack.peek();
			}
			stack.push(i);
		}
		return nsl;
	}

	public void increasingTriplet(int[] nums) {

		if (nums == null || nums.length == 0) {
			return;
		}
		int[] nsl = getNextSmallestToLeft(nums);
		int[] ngr = getNextGreaterToRight(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nsl[i] != -1 && ngr[i] != -1) {
				return;
			}
		}

    }

	public static void main(String[] args) {

		// int[] nums = { 1, 2, 3, 4, 5 };
		int[] nums = { 5, 4, 3, 2, 1 };
		new IncreasingTripletSubsequence().increasingTriplet(nums);
	}

}
