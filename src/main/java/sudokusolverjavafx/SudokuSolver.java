package sudokusolverjavafx;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Collections;
import java.util.HashSet;

// Refactor code for performance

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



    public boolean solved = false;

    public char[][] solveSudoku(char[][] board){  //**Make a separate function that actually does the recursive stuff, so I don't have to pass back the board.
        for (int i = 0; i<= 8; i++){ //iterating down the vertical
            for (int j = 0; j<= 8; j++){ //iterating across the row
                if (board[i][j] == '.'){ //Check if empty cell
                    for (int k =1; k<= 9; k++){ //iterate trying to put every integer into cell
                        board[i][j] = (char)(k + '0');
                        if (boardCheck(board[i], board[i][j], j, i, board)){
                            solveSudoku(board);
                            if (solved){
                                return board;
                            }
                        }

                        if (k == 9) {
                            board[i][j] = '.';
                            return board;
                        }

                        for (int l = 0; l < 9; l++){                           //These lines just print the board at every iteration.
                            System.out.println(Arrays.toString(board[l]));
                        };
                        System.out.println(" ");
                    }
                }
            }
        }
        solved = true;
        return board;
    }

    public boolean boardCheck(char[] row, char num, int numIndex, int rowIndex, char[][] board){

        // iterate over both the vertical and horizontal and check if it is unique.
        int xCounter = 0;
        int yCounter = 0;


        for (char digit : row){ // Checking if number is unique in row.
            if (digit == num){
                xCounter++;
            }
        }
        if (xCounter != 1){
            return false;
        }

        for (int i = 0; i <= 8; i++){  // Checking if number is unique in column.
            if (board[i][numIndex] == num){
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


//        for (char number : square){
//            if (number != '.' && Collections.frequency(square, number) != 1){ //Replace with for loop for performance
//                return false;
//            }
//        }

//        for (char i : square){
//            for (char k : square){
//                if (i == k){
//                    return false;
//                }
//            }
//        }


        if (square.size() != new HashSet<>(square).size()){ // HashSet is like a list but with only unique values. This is a quick way to check that the square has no duplicates.
            return false;
        };

        return true;
    }
}

//class Main {
//    public static void main(String[] args) {
//
//        char[][] board1 = {
//                {'5','3','.','.','7','.','.','.','.'},
//                {'6','.','.','1','9','5','.','.','.'},
//                {'.','9','8','.','.','.','.','6','.'},
//                {'8','.','.','.','6','.','.','.','3'},
//                {'4','.','.','8','.','3','.','.','1'},
//                {'7','.','.','.','2','.','.','.','6'},
//                {'.','6','.','.','.','.','2','8','.'},
//                {'.','.','.','4','1','9','.','.','5'},
//                {'.','.','.','.','8','.','.','7','9'}
//        };
//
//        char[][] board2 = {
//                {'.','.','9','7','4','8','.','.','.'},
//                {'7','.','.','.','.','.','.','.','.'},
//                {'.','2','.','1','.','9','.','.','.'},
//                {'.','.','7','.','.','.','2','4','.'},
//                {'.','6','4','.','1','.','5','9','.'},
//                {'.','9','8','.','.','.','3','.','.'},
//                {'.','.','.','8','.','3','.','2','.'},
//                {'.','.','.','.','.','.','.','.','6'},
//                {'.','.','.','2','7','5','9','.','.'}
//        };
//
//        char[][] board3 = {
//                {'.','.','4','6','9','2','7','3','1'},
//                {'3','1','6','8','5','7','9','2','4'},
//                {'9','2','7','1','3','4','6','8','5'},
//                {'6','4','1','2','8','9','5','7','3'},
//                {'8','9','2','5','7','3','4','1','6'},
//                {'7','5','3','4','1','6','8','9','2'},
//                {'1','6','5','9','2','8','3','4','7'},
//                {'4','7','9','3','6','1','2','5','8'},
//                {'2','3','8','7','4','5','1','6','9'}
//        };
//
//        char[][] board4 = {
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'},
//                {'.','.','.','.','.','.','.','.','.'}
//        };
//
//        SudokuSolver sudokuSolver = new SudokuSolver();
//
//        char[][] correctBoard = sudokuSolver.solveSudoku(board1);
//
//        for (int i = 0; i < 9; i++) {
//            System.out.println(Arrays.toString(correctBoard[i]));
//
//        }
//    }
//}