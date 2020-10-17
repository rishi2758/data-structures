package dp;

import java.util.concurrent.atomic.AtomicInteger;

public class MaximalSquare
{

    /**
     * [ 1 0 1 1] [ 0 1 1 1] [ 1 1 0 1] [ 1 1 1 1] ans : 2
     */

    public int maximalSquareArea(char[][] matrix)
    {
        AtomicInteger max = new AtomicInteger(0);
        for (int i = 0; i < matrix.length; i++) {
            solve(matrix, i, 0, max);
        }
        return max.get();
    }

    private void solve(char[][] matrix, int row, int col, AtomicInteger max)
    {
        if (row == matrix.length || col == matrix[0].length) {
            return;
        }

        if (matrix[row][col] == '1') {
            if ((row == 0 || col == 0)
                || (row - 1 > 0 && col - 1 > 0 && matrix[row - 1][col] == '1' && matrix[row][col - 1] == '1')) {
                max.set(Math.max(max.get(), (row-1)*row));
                solve(matrix, row + 1, col + 1, max);
            }
            solve(matrix, row, col + 1, max);
        }
    }

    private int getArea(int col)
    {
        return (col + 1) * (col + 1);
    }

    public static void main(String[] args)
    {
        char[][] matrix = {{'1', '0', '1', '1'}, {'0', '1', '1', '1'}, {'1', '1', '0', '1'}, {'1', '1', '1', '1'}};
        System.out.println(new MaximalSquare().maximalSquareArea(matrix));
    }

}
