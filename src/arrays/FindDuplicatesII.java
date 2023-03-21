package arrays;

public class FindDuplicatesII {

    public int removeDuplicates(int[] nums) {
        int validIdx = 0;
        int maxDuplicateAllowed = 1;
        for(int curr = 1; curr < nums.length; curr++) {
            if(nums[validIdx] == nums[curr]) {
                if(maxDuplicateAllowed > 0) {
                    nums[++validIdx] = nums[curr];
                    --maxDuplicateAllowed;
                }
            }else {
                maxDuplicateAllowed = 1;
                nums[++validIdx] = nums[curr];
            }
        }
        return validIdx+1;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,2,2,2,2,3,3,3,4,4,5};
        FindDuplicatesII fdii = new FindDuplicatesII();
        int k = fdii.removeDuplicates(nums);
        for(int i = 0; i < k;i++) {
            System.out.print(nums[i]+",");
        }
    }
}
