package arrays;

public class InsertIntervals {

    public void insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        int low = 0;
        int high = n - 1;
        //[[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (newInterval[0] <= intervals[mid][0]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        int[][] afterInsertion = new int[intervals.length + 1][2];
        int startCopy = 0;
        if (low != -1) {
            for (int i = 0; i < low; i++) {
                afterInsertion[startCopy++] = intervals[i];
            }
        }
        afterInsertion[startCopy++] = newInterval;
        for (int i = low ; i < n; i++) {
            afterInsertion[startCopy++] = intervals[i];
        }

        new MergeIntervals().mergeInterval(intervals);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval = {4,8};
        new InsertIntervals().insert(intervals,newInterval);
    }

}
