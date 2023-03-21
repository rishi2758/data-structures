package slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int i = 0;
        for(; i < k;i++) {
            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]){
                dq.removeLast();
            }
            dq.addLast(i);
        }
        int idx = 0;
        for(;i < n;i++){
            ans[idx++] = nums[dq.peekFirst()];

            while(!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.removeFirst();
            }

            while(!dq.isEmpty() && nums[i] >= nums[dq.peekLast()]) {
                dq.removeLast();
            }

            dq.addLast(i);
        }
        ans[idx] = nums[dq.peekFirst()];
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] nums2 = {1};
        int[] res = new MaxSlidingWindow().maxSlidingWindow(nums2, 1);
        for (int j : res) {
            System.out.print(j + " | ");
        }
        for(int k  = 1 ; k < nums.length ; k++){
            res = new MaxSlidingWindow().maxSlidingWindow(nums, k);
            for (int re : res) {
                System.out.print(re + " | ");
            }
            System.out.println();
        }
    }

}
