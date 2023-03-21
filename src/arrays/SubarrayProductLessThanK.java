package arrays;

public class SubarrayProductLessThanK {

    public void countSubarrayLessThanK(int[] nums, int k) {

        int n = nums.length;
        int b = 0;
        int f = 1;

        int currProduct = nums[0];
        int count = 0;

        while (true) {

            boolean f1 = false;
            boolean f2 = false;

            while (f < n) {
                f1 = true;
                currProduct *= nums[f];
                if (currProduct  < k) {
                    count++;
                } else {
                    break;
                }
                ++f;
            }

            if(b == f && f < n) {
                b++;
                f=b+1;
                currProduct = nums[b];
            }else {
                while (b < f) {
                    f2 = true;
                    currProduct /= nums[b++];
                    if (currProduct < k) {
                        break;
                    }
                }
            }

            if (!f1 && !f2) {
                break;
            }

        }

    }

    public static void main(String[] args) {

        int[] nums = {10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3};
        int k = 100;
        int[] nums1 = {10, 5, 2, 6};
        new SubarrayProductLessThanK().countSubarrayLessThanK(nums, 19);

    }

}
