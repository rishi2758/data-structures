package rangequery;

import fastIo.FastReader;

import java.io.*;

/**
 * There are N numbers A1, A2.... An , and you are given k queries.
 * In each query, you are given two integers l & r.
 * You are required to print the sum of all the numbers whose
 * frequency of occurrence is between l and r (including l and r ).
 * <p>
 * Print a single integer for each query in a new line.
 * <p>
 * Input :
 * 8
 * 4 4 6 5 3 3 3 9
 * 4
 * 1 4
 * 2 7
 * 3 7
 * 5 6
 * <p>
 * Output :
 * 37
 * 17
 * 9
 * 0
 */
public class SumAsPerFrequency {

    static class RangeQuery {

        private final int[] intFreq;
        private final long[] sum;

        public RangeQuery() {
            intFreq = new int[1000001];
            sum = new long[1000001];
        }

        public void add(int num) {
            int prevFreq = intFreq[num];
            int newFreq = prevFreq + 1;
            intFreq[num] = intFreq[num]++;
            if (prevFreq != 0) {
                sum[prevFreq] -= (long) num * prevFreq;
            }
            sum[newFreq] += (long) num * newFreq;
        }

        private void computePrefixSum() {
            for (int i = 1; i < sum.length; i++) {
                sum[i] += sum[i - 1];
            }
        }

        public long getSum(int l, int r) {
            if ((l >= sum.length && r >= sum.length) || (l < 0 && r < 0)) {
                return 0;
            }

            l = Math.max(l, 1);
            r = Math.min(r, sum.length - 1);

            return sum[r] - sum[l - 1];
        }
    }

    public static void readInput() throws IOException {

        FastReader reader = new FastReader();
        int n = reader.nextInt();
        RangeQuery rQuery = new RangeQuery();
        for (int i = 0; i < n; i++) {
            rQuery.add(reader.nextInt());
        }
        rQuery.computePrefixSum();
        int q = reader.nextInt();
        while(q > 0) {
            int l = reader.nextInt();
            int r = reader.nextInt();
            long rangeSum = rQuery.getSum(l,r);
            System.out.println(rangeSum);
            q--;
        }

    }

    public static void main(String[] args) throws Exception {
        FileInputStream fs = new FileInputStream("/Users/rishirajsingh/Documents/official/input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
        int n = Integer.parseInt(br.readLine());
        String[] nums = br.readLine().split(" ");
        RangeQuery rQuery = new RangeQuery();
        for (int i = 0; i < n; i++) {
            rQuery.add(Integer.parseInt(nums[i]));
        }
        rQuery.computePrefixSum();
        int k = Integer.parseInt(br.readLine());
        FileInputStream fisExpected = new FileInputStream("/Users/rishirajsingh/Documents/official/expected.txt");
        BufferedReader brE = new BufferedReader(new InputStreamReader(fisExpected));
        FileOutputStream fos = new FileOutputStream("/Users/rishirajsingh/Documents/official/output.txt");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        while (k-- > 0) {
            String[] query = br.readLine().split(" ");
            int l = Integer.parseInt(query[0]);
            int r = Integer.parseInt(query[1]);
            long sum = rQuery.getSum(l, r);
            String actualSum = String.valueOf(sum);
            String expectedSum = brE.readLine();
            bw.write(expectedSum + " :: " + actualSum + (expectedSum.equals(actualSum) ? " -> PASSED" : " -> FAILED"));
            bw.write("\n");
        }
        bw.close();
    }
}
