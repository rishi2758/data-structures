package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens
{

    public char[][] placeQueens(int queens)
    {
        if (queens == 0)
            return null;
        char[][] board = new char[queens][queens];
        // indicate whether placement is done or not.
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        System.out.println("total Placements : "+_placeQueens(board, 0));
        return board;
    }

    private void _placeQueens(char[][] board, int col , List<List<String>> validPlacements)
    {
        if (col >= board.length) {
            List<String> result = new ArrayList<>();
            for (char[] chars : board) {
                StringBuilder eachRow = new StringBuilder();
                for (int v = 0; v < board[0].length; v++) {
                    eachRow.append(chars[v]);
                }
                result.add(eachRow.toString());
            }
            validPlacements.add(result);
            return;
        }
        for (int queen = 0; queen < board.length; queen++) {
            if (validateQueenPlacement(board, queen, col)) {
                board[queen][col] = 'Q';
                _placeQueens(board, col + 1,validPlacements);
                board[queen][col] = '.';
            }
        }
    }

    private int _placeQueens(char[][] board, int col)
    {
        if (col >= board.length) {
            return 1;
        }
        int validPlacements = 0;
        for (int queen = 0; queen < board.length; queen++) {
            if (validateQueenPlacement(board, queen, col)) {
                board[queen][col] = 'Q';
                validPlacements +=_placeQueens(board, col + 1);
                board[queen][col] = '.';
            }
        }
        return validPlacements;
    }
    
    
    private boolean validateQueenPlacement(char[][] board, int queen, int col)
    {
        int i, j;
        for (i = 0; i < col; i++) {
            if (board[queen][i] == 'Q')
                return false;
        }

        /* Check upper diagonal on left side */
        for (i = queen, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 'Q')
                return false;

        /* Check lower diagonal on left side */
        for (i = queen, j = col; j >= 0 && i < board.length; i++, j--)
            if (board[i][j] == 'Q')
                return false;

        return true;

    }

    public static void main(String[] args)
    {
        char[][] queensPlaced = new NQueens().placeQueens(3);

        for (char[] chars : queensPlaced) {
            for (int j = 0; j < queensPlaced[0].length; j++) {
                System.out.print(chars[j]);
            }
            System.out.println();
        }
    }

}
