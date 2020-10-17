package dp;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class CandyArrangement
{

    public int arrange(int n, int m, int k)
    {
        char[] arr = new char[n + m];
        Arrays.fill(arr, ' ');
        AtomicInteger ans = new AtomicInteger(0);
        place(n-1,m, k, 0, 'R', arr, ans);
        Arrays.fill(arr, ' ');
        place(n, m-1, k, 0, 'B', arr, ans);
        return ans.get();
    }

    private void place(int n, int m, int k, int currIdx, char curr, char[] arr, AtomicInteger ans)
    {
        if (currIdx == arr.length) {
            if (n == 0 && m == 0) {
                ans.set(ans.get() + 1);
            }
            return;
        }

        if (canPlace(curr, currIdx, k, arr)) {
            arr[currIdx] = curr;
            place(n - 1, m, k, currIdx + 1, 'R', arr, ans);
            place(n, m - 1, k, currIdx + 1, 'B', arr, ans);
        }else {
            arr[currIdx] = ' ';
        }

    }

    private boolean canPlace(char curr, int currIdx, int k, char[] arr)
    {
        if (currIdx == 0) {
            return true;
        }
        int b = k;
        int now = 0;
        while (currIdx > 0 && b-- > 0 && arr[--currIdx] == curr) {
            ++now;
        }
        if (now < k) {
            return true;
        }
        return false;
    }

    public static void main(String[] args)
    {
        new CandyArrangement().arrange(2, 1, 1);
    }

}
