package misc;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

	public List<Integer> spiralOrder(int[][] matrix) {
		/*
		 * move right until hit boundary /visited then move down unit hit boundary
		 * /visited then move left unit hit boundary /visited then move up until hit
		 * boundary/visited repeat until all elements are visited. .
		 */

		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		List<Integer> ans = new ArrayList<>();
		int row = 0;
		int col = 0;
		int count = 0;
        while (count != matrix.length * matrix[0].length) {

            // move right
            while (row < matrix.length && col < matrix[row].length && !visited[row][col]) {
                ++count;
                visited[row][col] = true;
                ans.add(matrix[row][col]);
                ++col;
            }
            ++row;
            --col;
            // move down
            while (row < matrix.length && col < matrix[row].length && !visited[row][col]) {
                ++count;
                visited[row][col] = true;
                ans.add(matrix[row][col]);
                ++row;
            }
            --row;
            --col;
            // move left
            while (row < matrix.length && col >= 0 && !visited[row][col]) {
                ++count;
                visited[row][col] = true;
                ans.add(matrix[row][col]);
                --col;
            }
            ++col;
            --row;
            // move up
            while (row >= 0 && col < matrix[row].length && !visited[row][col]) {
                ++count;
                visited[row][col] = true;
                ans.add(matrix[row][col]);
                --row;
            }
            ++row;
            ++col;
        }

		return ans;
	}

	public static void main(String[] args) {

		int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };

		new SpiralOrder().spiralOrder(matrix).forEach(System.out::println);


	}

}
