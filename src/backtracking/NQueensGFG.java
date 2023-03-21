package backtracking;

public class NQueensGFG
{
    //static int N = 4;

    static boolean isSafe(int[][] board, int row, int col)
    {
        int i, j;
        for (i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        for (i = row, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solveNQUtil(int[][] board, int col)
    {
        if (col >= board.length)
            return true;

        for (int i = 0; i < board.length; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQUtil(board, col + 1))
                    return true;

                board[i][col] = 0; // BACKTRACK
            }
        }

        return false;
    }

    public static void main(String[] args)
    {
        int[][] board = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

        if (!solveNQUtil(board, 0)) {
            System.out.print("Solution does not exist");
        }
        for (int[] ints : board) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(" " + ints[j] + " ");
            System.out.println();
        }
    }

}
