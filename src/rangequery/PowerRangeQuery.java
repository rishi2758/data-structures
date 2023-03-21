package rangequery;

import java.util.LinkedList;

public class PowerRangeQuery {

	//private static final int MOD = 1000000007;

	public int[] productQueries(int n, int[][] queries) {
		LinkedList<Integer> powers = new LinkedList<>();
		while (n > 0) {
			int maxPower = extractMaxPowerOf2(n);
			int power = (int) Math.pow(2, maxPower);
			powers.addFirst(power);
			n -= power;
		}
		long[] computedProduct = new long[powers.size()];
		int[] cz = new int[powers.size()];

		long product = 1;
		int count = 0;
		for (int i = 0; i < powers.size(); i++) {
			if (powers.get(i) == 0) {
				++count;
			} else {
				product = product * powers.get(i);
			}
			computedProduct[i] = product;
			cz[i] = count;
		}

		int[] ans = new int[queries.length];
		int idx = 0;
		for (int[] query : queries) {
			int start = query[0];
			int end = query[1];

			if (start == 0) {
				if (cz[end] == 0) {
					ans[idx++] = (int) computedProduct[end];
				} else {
					ans[idx++] = 0;
				}
			} else {
				if (cz[end] - cz[start - 1] == 0) {
					ans[idx++] = (int) (computedProduct[end] / computedProduct[start - 1]);
				} else {
					ans[idx++] = 0;
				}
			}
		}
		return ans;
	}

	private int extractMaxPowerOf2(int n) {
		int maxPow = 0;
		long power = 1;
		while (power <= n) {
			power = (long) Math.pow(2, maxPow++);
		}
		return maxPow - 2;
	}

	public static void main(String[] args) {
		int[][] queries = { { 0, 1 }, { 2, 2 }, { 0, 3 } };
		for (int productQueries : new PowerRangeQuery().productQueries(15, queries)) {
			System.out.print(productQueries);
		}
	}

}
