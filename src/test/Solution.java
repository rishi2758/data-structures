package test;

import java.util.HashMap;
import java.util.Map;

/*
*   Input: Fruit=['A', 'B', 'C', 'A', 'C']
    Output: 3

    Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
    Output: 5
    *
    *
    //garden , row of trees with different fruits
    //order of trees user will decide.
    //maximum num of fruits from trees
    //2 baskets no two trees
    //pick anywhere
* */
public class Solution {

    public int pickMaximumFruits(char[] fruits) {

        Map<Character, Integer> fruitFreq = new HashMap<>();
        int start = 0;
        int end = 0;
        int n = fruits.length;

        int ans = 0;
        //{'A', 'B', 'C', 'A', 'C'};
        //{'A', ,'B', 'B', 'C', 'A', 'C'};

        while (end < n) {
            while (fruitFreq.size() > 2) {
                int f = fruitFreq.get(fruits[start]);
                if (f == 1) {
                    fruitFreq.remove(fruits[start]);
                } else {
                    fruitFreq.put(fruits[start], f - 1);
                }
                ++start;
            }

            if (fruitFreq.size() == 2) {
                int p = 0;
                for (int f : fruitFreq.values()) {
                    p += f;
                }
                ans = Math.max(ans, p);
            }
            fruitFreq.put(fruits[end], fruitFreq.getOrDefault(fruits[end], 0) + 1);
            ++end;
        }

        int p = 0;
        for (int f : fruitFreq.values()) {
            p += f;
        }
        ans = Math.max(ans, p);

        return ans;
    }

    public static void main(String[] args) {
        char[] Fruit = {'A', 'B', 'C', 'A', 'C'};
        char[] Fruit2 = {'A', 'B', 'C', 'B', 'B', 'C'};
        int ans = new Solution().pickMaximumFruits(Fruit2);
        System.out.println(ans);
    }

}
