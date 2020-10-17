package arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class FirstMissing
{

    public int firstMissingPositive(ArrayList<Integer> A)
    {
        int n = A.size();
        int start = 0;
        int currIdx = 0;
        while (currIdx < n) {
            int temp = A.get(currIdx);
            if (temp < n && temp > 0 && currIdx != temp) {
                A.set(temp - 1, 1);
                currIdx = temp;
            } else {
                currIdx = ++start;
            }

        }
        for (int i = 0; i < n; i++) {
            if (A.get(i) < 0 || A.get(i) > n) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args)
    {
        new FirstMissing().firstMissingPositive(new ArrayList<>(Arrays.asList(1, 1, 1 )));
    }
}
