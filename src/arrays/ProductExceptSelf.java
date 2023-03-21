package arrays;

public class ProductExceptSelf
{

    public int[] productExceptSelf(int[] nums)
    {

        // [1,2,3,4,5]
        // [1,2,6,24,120]
        // [120,120,60,20,5]
        // [120,60,40,30,24]
        // [0,0]
        // [1,0,0]
        // [0,0,1]
        int n = nums.length;
        int[] prefix = new int[n];
        prefix[0] = 1;
        for (int i = 1; i < n; ++i) {
            prefix[i] = prefix[i - 1] * nums[i-1];
        }
        int[] suffix = new int[n];
        suffix[suffix.length - 1] = 1;
        for (int i = suffix.length - 2; i >= 0; --i) {
            suffix[i] = suffix[i + 1] * nums[i+1];
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
                res[i] = prefix[i]*suffix[i];
        }
        return res;
    }

    public static void main(String[] args)
    {

        int[] nums = {0,1};
        int[] res = new ProductExceptSelf().productExceptSelf(nums);
        for(Integer i : res) {
            System.out.print(i+"-");
        }
    }

}
