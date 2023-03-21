package arrays;

public class ZeroFilledSubarray {

    public long zeroFilledSubarray(int[] nums) {
        long cnt = 0L;
        long local = 0L;
        for (int n : nums) {
            if (n == 0) {
                cnt += ++local;  // eg. 0000 // cnt = 1 local = 1 -> cnt = 3 local = 2 -> cnt = 6 local = 3 -> cnt = 10 local = 4
            } else {
                local = 0;
            }
        }
        return cnt;
    }

    public long _naivezeroFilledSubarray(int[] nums) {

        int n = nums.length;
        long count = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 0) {
                int j = i;
                while(j < n && nums[j] == 0) {
                    ++j;
                }
                count += (j-i) * (j-i+1)/2 ; // this causes overflow because of product of two ints , we need to build the ans incrementally hence the above approach
                i = j;
            }
        }
        return count;
    }

}
