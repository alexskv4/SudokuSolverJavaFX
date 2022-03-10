package sudokusolverjavafx;

import java.util.ArrayList;
import java.util.HashSet;

public class SudokuSolver {

    public static final char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
    };

    public static final char[][] board2 = {
            {'.','.','9','7','4','8','.','.','.'},
            {'7','.','.','.','.','.','.','.','.'},
            {'.','2','.','1','.','9','.','.','.'},
            {'.','.','7','.','.','.','2','4','.'},
            {'.','6','4','.','1','.','5','9','.'},
            {'.','9','8','.','.','.','3','.','.'},
            {'.','.','.','8','.','3','.','2','.'},
            {'.','.','.','.','.','.','.','.','6'},
            {'.','.','.','2','7','5','9','.','.'}
    };

    public static final char[][] board3 = {
            {'.','.','4','6','9','2','7','3','1'},
            {'3','1','6','8','5','7','9','2','4'},
            {'9','2','7','1','3','4','6','8','5'},
            {'6','4','1','2','8','9','5','7','3'},
            {'8','9','2','5','7','3','4','1','6'},
            {'7','5','3','4','1','6','8','9','2'},
            {'1','6','5','9','2','8','3','4','7'},
            {'4','7','9','3','6','1','2','5','8'},
            {'2','3','8','7','4','5','1','6','9'}
    };

    public static final char[][] board4 = {
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'},
            {'.','.','.','.','.','.','.','.','.'}
    };

    public char[][] solveSudoku(char[][] board){
        solve(board);
        return board;
    }

    private boolean solve (char[][] board){
        for (int i = 0; i<= 8; i++){ //iterating down the vertical
            for (int j = 0; j<= 8; j++){ //iterating across the row
                if (board[i][j] == '.'){ //Check if empty cell
                    for (int k =1; k<= 9; k++){ //iterate trying to put every integer into cell
                        board[i][j] = (char)(k + '0');
                        
                        if (boardCheck(j, i, board)){
                            if (solve(board)){
                                return true;
                            }
                        }

                        if (k == 9) {
                            board[i][j] = '.';
                            return false;
                        }

//                        for (int l = 0; l < 9; l++){                           //These lines just print the board at every iteration.
//                            System.out.println(Arrays.toString(board[l]));
//                        };
//                        System.out.println(" ");
                    }
                }
            }
        }
        return true;
    }

    public static boolean boardCheck(int numIndex, int rowIndex, char[][] board){

        // iterate over both the vertical and horizontal and check if it is unique.
        int xCounter = 0;
        int yCounter = 0;


        for (char digit : board[rowIndex]){ // Checking if number is unique in row.
            if (digit == board[rowIndex][numIndex]){
                xCounter++;
            }
        }
        if (xCounter != 1){
            return false;
        }

        for (int i = 0; i <= 8; i++){  // Checking if number is unique in column.
            if (board[i][numIndex] == board[rowIndex][numIndex]){
                yCounter++;
            }
        }
        if (yCounter != 1){
            return false;
        }


        int cornerVertIndex = rowIndex - (rowIndex % 3);
        int cornerHoriIndex = numIndex - (numIndex % 3);
        ArrayList<Character> square = new ArrayList<>();

        for (int i = cornerVertIndex; i < cornerVertIndex + 3; i++){
            for (int j = cornerHoriIndex; j < cornerHoriIndex + 3; j++){
                if (board[i][j] == '.'){
                    continue;
                }
                square.add(board[i][j]);
            }
        }

        return square.size() == new HashSet<>(square).size();
    }
}
