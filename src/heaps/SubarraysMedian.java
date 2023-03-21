package heaps;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SubarraysMedian {
    public static void getMedianSubarrays(List<Integer> efficiency, int k) {
        // Write your code here
        int n = efficiency.size();

        if (k > n) {
            return;
        }

        int median = efficiency.get(k - 1);

        int ans = 0;

        for (int start = 0; start < n; ++start) {
            for (int end = start; end < n; end += 2) {
                PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
                int windowLen = end - start + 1;
                int midIndex = (windowLen + 1) / 2;
                if (windowLen % 2 != 0) {
                    for (int i = start; i <= end; i++) {
                        maxHeap.offer(efficiency.get(i));
                    }
                    while (!maxHeap.isEmpty()) {
                        --midIndex;
                        int e = maxHeap.poll();
                        if (midIndex == 0 && median == e) {
                            ++ans;
                        }
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        getMedianSubarrays(Arrays.asList(1, 2, 3), 1);
    }

}
