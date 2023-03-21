package cyclicsort;

import java.util.Arrays;

public class CorruptPair {

    private void findCorruptPair(int[] nums) {

        int n = nums.length;
        int i = 0;

        while(i < n) {

            int correctIndex = nums[i];
            if(nums[i] != nums[correctIndex-1]) {
                int tmp = nums[correctIndex-1];
                nums[correctIndex-1] = nums[i];
                nums[i] = tmp;
            }else {
                ++i;
            }
        }

        int[] ans = new int[2];
        Arrays.fill(ans, -1);
        for(i = 0; i < n;i++) {
            if(i+1 != nums[i]) {
                ans[0] = i+1;
                ans[1] = nums[i];
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,2,5,3,4};
        new CorruptPair().findCorruptPair(nums);
    }

}
