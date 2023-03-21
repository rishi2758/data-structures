package misc;

import java.util.Arrays;

public class MinPathSum {

	public int minPathSum(int[][] grid) {
		int[][] dp = new int[grid.length][];
		int colLen = 0;
		for (int row = 0; row < grid.length; row++) {
			colLen = grid[row].length;
			dp[row] = new int[colLen];
			Arrays.fill(dp[row], Integer.MAX_VALUE);
			for (int col = 0; col < grid[row].length; col++) {
				if (row == 0 && col == 0) {
					dp[row][col] = grid[row][col];
				} else if (row == 0) {
					dp[row][col] = dp[row][col - 1] + grid[row][col];
				} else if (col == 0) {
					dp[row][col] = dp[row - 1][col] + grid[row][col];
				} else {
					int left = grid[row][col] + dp[row][col - 1];
					int top = grid[row][col] + dp[row - 1][col];
					dp[row][col] = Math.min(left, top);
				}
			}
		}

		return dp[grid.length - 1][colLen - 1];
	}

	public static void main(String[] args) {
		int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 } };
		System.out.println(new MinPathSum().minPathSum(grid));
	}

}
