package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WaterFlow {

	public List<List<Integer>> pacificAtlantic(int[][] heights) {
		List<List<Integer>> ans = new ArrayList<>();

		for (int i = 0; i < heights.length; i++) {
			for (int j = 0; j < heights[i].length; j++) {
				if (canFlowIntoPacificAndAtlantic(heights, i, j, new HashSet<>())) {
					ans.add(Arrays.asList(i, j));
				}
			}
		}

		return ans;
	}

	private boolean canFlowIntoPacificAndAtlantic(int[][] heights, int row, int col, Set<String> visited) {

		if (row < 0 || col < 0 || row > heights.length - 1 || col > heights[row].length - 1) {
			return true;
		}

		visited.add(getKey(row, col));

		boolean up = row == 0;
		boolean down = row == heights.length - 1;
		boolean left = col == 0;
		boolean right = col == heights[row].length - 1;

		if (row - 1 >= 0 && heights[row][col] >= heights[row - 1][col] && !visited.contains(getKey(row - 1, col))) {
			up = canFlowIntoPacificAndAtlantic(heights, row - 1, col, visited);
		}
		if (col - 1 >= 0 && heights[row][col] >= heights[row][col - 1] && !visited.contains(getKey(row, col - 1))) {
			left = canFlowIntoPacificAndAtlantic(heights, row, col - 1, visited);
		}
		if (col + 1 <= heights[row].length - 1 && heights[row][col] >= heights[row][col + 1]
				&& !visited.contains(getKey(row, col + 1))) {
			right = canFlowIntoPacificAndAtlantic(heights, row, col + 1, visited);
		}
		if (row + 1 <= heights.length - 1 && heights[row][col] >= heights[row + 1][col]
				&& !visited.contains(getKey(row + 1, col))) {
			down = canFlowIntoPacificAndAtlantic(heights, row + 1, col, visited);
		}

		return (up && (right || down)) || (left && (right || down)) || (right && (up || left))
				|| (down && (up || left));
	}

	private String getKey(int row, int col) {
		return String.valueOf(row).concat("#").concat(String.valueOf(col));
	}

	public static void main(String[] args) {
		int[][] heights = { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 },
				{ 5, 1, 1, 2, 4 } };
		// new WaterFlow().pacificAtlantic(heights);
		boolean test = new WaterFlow().canFlowIntoPacificAndAtlantic(heights, 1, 3, new HashSet<>());
		System.out.println(test);
	}

}
