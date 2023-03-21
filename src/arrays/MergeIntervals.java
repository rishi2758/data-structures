package arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    public int[][] mergeInterval(int[][] intervals) {

        //sort them on their start times.
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        int n = intervals.length;
        int b = 0;
        int f = 0;
        List<int[]> ans = new ArrayList<>();
        while(f < n) {
            int[] currentInterval = intervals[f];
            int size = ans.size()-1;
            if(ans.isEmpty()) {
                ans.add(currentInterval);
            }else {
                int[] prev = ans.get(size);
                if(currentInterval[0] <= prev[1]) {
                    ans.set(size,new int[]{prev[0],Math.max(prev[1],currentInterval[1])});
                }else {
                    ans.add(currentInterval);
                }
            }
            ++f;
        }

        int[][] a = new int[ans.size()][2];
        int i = 0;
        for(int[] interval : ans) {
            a[i++] = interval;
        }
        return a;
    }

    public static void main(String[] args) {
    //[1,2],[2,3],[2,4],[5,8]
        int[][] intervals = {{1,2},{2,3},{5,8},{2,4}};

        for (int[] ints : new MergeIntervals().mergeInterval(intervals)) {
            System.out.println("("+ints[0]+","+ints[1]+")");
        }

    }

}
