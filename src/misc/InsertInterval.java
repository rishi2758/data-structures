
package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {

	public void insert(int[][] intervals, int[] newInterval) {

		ArrayList<List<Integer>> ans = new ArrayList<>();
		ans.add(Arrays.asList(newInterval[0], newInterval[1]));
		for (int[] interval2 : intervals) {

			int currStart = interval2[0];
			int currEnd = interval2[1];
			int n = ans.size() - 1;
			if (currStart < ans.get(n).get(0)) {
                List<Integer> t;
                if (currEnd < ans.get(n).get(0)) {
                    t = ans.get(n);
					ans.remove(n);
					ans.add(Arrays.asList(currStart, currEnd));
                } else {
                    t = Arrays.asList(Math.min(ans.get(n).get(0), currStart),
                            Math.max(ans.get(n).get(1), currEnd));
					ans.remove(n);
                }
                ans.add(t);
            } else {
				if (currStart > ans.get(n).get(1)) {
					ans.add(Arrays.asList(currStart, currEnd));
				} else {
					List<Integer> t = Arrays.asList(Math.min(ans.get(n).get(0), currStart),
							Math.max(ans.get(n).get(1), currEnd));
					ans.remove(n);
					ans.add(t);
				}
			}

		}

		int[][] at = new int[ans.size()][2];
		for (int i = 0; i < ans.size(); i++) {
			at[i][0] = ans.get(i).get(0);
			at[i][1] = ans.get(i).get(1);
		}
	}

	public static void main(String[] args) {
		int[][] intervals = { { 1, 3 }, { 6, 9 } };
		int[] newInterval = { 2, 5 };
//		int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
//		int[] newInterval = { 4, 8 };
		// intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
		new InsertInterval().insert(intervals, newInterval);
	}

}
