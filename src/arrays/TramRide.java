package arrays;

import java.util.Arrays;

public class TramRide {

    static void solve(int N, int start, int finish, int[] cost) {

        long[] prefixSum = new long[N];
        prefixSum[0] = cost[0];

        for (int i = 1; i < N; i++) {
            prefixSum[i] = prefixSum[i - 1] + cost[i];
        }

        int startIdx = start - 1;
        int endIdx = finish - 1;
        long result = 0;

        if (startIdx < endIdx) {
            long costToEnd = getCostBetween(startIdx, endIdx, prefixSum);
            long costToEndRev = cost[N - 1] + getCostBetween(0, startIdx, prefixSum) + getCostBetween(endIdx, N - 1, prefixSum);
            result = Math.min(costToEnd, costToEndRev);
        } else {
            long costToEnd = 0;
            for (int k = startIdx - 1; k >= endIdx; k--) {
                costToEnd += cost[k];
            }
            long costToEndRev = cost[N - 1] + getCostBetween(0, endIdx, prefixSum) + getCostBetween(startIdx, N - 1, prefixSum);
            result = Math.min(costToEnd, costToEndRev);
        }

        System.out.println("N " + N + " start " + start + " finish " + finish + " ticket_cost " + Arrays.toString(cost) + " MIN_COST :: " + result);
    }

    static private long getCostBetween(int i, int j, long[] prefixSum) {
        long result = 0;
        if (i == j) {
            result = 0;
        } else if (j - 1 > 0) {
            long costToEnd = prefixSum[j - 1];
            if (i > 0) {
                long costToStart = prefixSum[i - 1];
                costToEnd = costToEnd - costToStart;
            }
            result = costToEnd;
        }
        System.out.println(" costBetween start " + i + " end " + j + " prefixSum " + Arrays.toString(prefixSum) + " cost " + result);
        return result;
    }

    public static void main(String[] args) {
        int[] cost = {1, 2, 3, 4};
        int[] cost2 = {12, 6, 2, 6, 2};
        System.out.println("---------------");
        solve(5, 4, 3, cost2);
        System.out.println("---------------");
        solve(4, 1, 4, cost);
        System.out.println("---------------");
        solve(4, 2, 4, cost);
        System.out.println("---------------");
        solve(4, 3, 4, cost);
        System.out.println("---------------");
        solve(4, 4, 4, cost);
        System.out.println("---------------");
        solve(4, 1, 3, cost);
        System.out.println("---------------");
        solve(4, 1, 2, cost);
        System.out.println("---------------");
        solve(4, 1, 1, cost);
        System.out.println("---------------");
        solve(4, 2, 3, cost);
        System.out.println("---------------");
        solve(4, 2, 2, cost);
        System.out.println("---------------");
        solve(4, 2, 1, cost);
        System.out.println("---------------");
        solve(4, 3, 3, cost);
        System.out.println("---------------");
        solve(4, 3, 2, cost);
        System.out.println("---------------");
        solve(4, 3, 1, cost);
        System.out.println("---------------");
        solve(4, 4, 3, cost);
        System.out.println("---------------");
        solve(4, 4, 2, cost);
        System.out.println("---------------");
        solve(4, 4, 1, cost);
        System.out.println("---------------");
    }
}
