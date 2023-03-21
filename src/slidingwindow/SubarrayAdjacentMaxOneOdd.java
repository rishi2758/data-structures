package slidingwindow;

import java.util.*;
import java.util.stream.Collectors;

public class SubarrayAdjacentMaxOneOdd {

    public List<List<Integer>> findSubarray(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();

        for (int num : nums) {
            ans.add(Collections.singletonList(num));
        }

        int f = 0;
        int b = 0;
        int n = nums.length;
        boolean oddAdded = false;
        while (true) {
            boolean f1 = false;
            boolean f2 = false;

            List<Integer> subarray = new ArrayList<>();
            while (f < n) {
                f1 = true;
                if (nums[f] % 2 != 0) {
                    if (!oddAdded) {
                        oddAdded = true;
                        subarray.add(nums[f]);
                    } else {
                        break;
                    }
                } else {
                    subarray.add(nums[f]);
                }
                ++f;
            }
            if (!subarray.isEmpty()) {
                ans.add(subarray);
            }
            while (b < f) {
                f2 = true;
                if (nums[b] % 2 != 0) {
                    if (oddAdded) {
                        oddAdded = false;
                        break;
                    }
                }
                ++b;
            }

            if (!f1 && !f2) {
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 8, 6, 10, 11, 13, 15, 17, 19, 21};
        String ans = new SubarrayAdjacentMaxOneOdd().findSubarray(nums).stream().map(String::valueOf).collect(Collectors.joining());
        System.out.println(ans);
    }

}
