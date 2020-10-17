package backtracking;

public class SudokuSolver
{

    public void solveSudoku(char[][] board)
    {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }
        _solveSudoku(board,0,0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private boolean _solveSudoku(char[][] board,int row, int col)
    {
        if (col == board[row].length) {
            col = 0;
            ++row;
            if(row == board.length) {
                return true;
            }
        }
        if (board[row][col] != '0') {
           return _solveSudoku(board, row, col+1);
        }
        for (int choice = 1; choice <= board.length; choice++) {
            char charToPlace = (char) (choice + '0');
            if (isValid(board, row, col, charToPlace)) {
                board[row][col] = charToPlace;
                if(_solveSudoku(board, row, col + 1))
                    return true;
                board[row][col] = '0';
            } 
        }
        return false;
    }

    private boolean isValid(char[][] board, int row, int col,int charToPlace)
    {
        for (char[] placementRow: board) {
            if (charToPlace == placementRow[col]){
              return false;
            }
          }

          // Check row of the placement
          for (int i = 0; i < board[row].length; i++) {
            if (charToPlace == board[row][i]) {
              return false;
            }
          }

          // Check region constraints - get the size of the sub-box
          int regionSize = (int) Math.sqrt(board.length);

          int verticalBoxIndex = row / regionSize;
          int horizontalBoxIndex = col / regionSize;

          int topLeftOfSubBoxRow = regionSize * verticalBoxIndex;
          int topLeftOfSubBoxCol = regionSize * horizontalBoxIndex;

          for (int i = 0; i < regionSize; i++) {
            for (int j = 0; j < regionSize; j++) {
              if (charToPlace == board[topLeftOfSubBoxRow + i][topLeftOfSubBoxCol + j]) {
                return false;
              }
            }
          }

          return true;
    }

    public static void main(String[] args)
    {
        
        System.out.println(4%3);
        char[][] board = {{'3', '0', '6', '5', '0', '8', '4', '0', '0'}, {'5', '2', '0', '0', '0', '0', '0', '0', '0'},
            {'0', '8', '7', '0', '0', '0', '0', '3', '1'}, {'0', '0', '3', '0', '1', '0', '0', '8', '0'},
            {'9', '0', '0', '8', '6', '3', '0', '0', '5'}, {'0', '5', '0', '0', '9', '0', '6', '0', '0'},
            {'1', '3', '0', '0', '0', '0', '2', '5', '0'}, {'0', '0', '0', '0', '0', '0', '0', '7', '4'},
            {'0', '0', '5', '2', '0', '6', '3', '0', '0'}};
        new SudokuSolver().solveSudoku(board);
    }

}
