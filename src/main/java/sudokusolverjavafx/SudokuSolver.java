package sudokusolverjavafx;

import java.util.ArrayList;
import java.util.HashSet;

public class SudokuSolver {

    private SudokuGrid grid;
    private char[][] board;

    public SudokuSolver (SudokuGrid grid) {
        this.grid = grid;
        this.board = SudokuBoards.board4;
    }

    public void setBoard (char[][] board) {
        this.board = board;
    }
    public char[][] getBoard () {return board;}

    public boolean solve (){
        for (int i = 0; i<= 8; i++){ //iterating down the vertical
            for (int j = 0; j<= 8; j++){ //iterating across the row
                if (board[i][j] == '.'){ //Check if empty cell
                    for (int k =1; k<= 9; k++){ //iterate trying to put every integer into cell
                        if (grid.showBacktracking) {
                            grid.updateIterationCounter(grid); //todo: Make iteration counter work when backtracking is not shown. (Performance issues)
                        }
                        board[i][j] = (char)(k + '0');
                        if (grid.isSolverStopped) {
                            return true;
                        }
                        if (boardCheck(j, i)){
                            if(grid.showBacktracking) {
                                grid.updateGuiCell(String.valueOf(k), j, i, true);
                            }
                            if (solve()){
                                return true;
                            }
                        }
                        if (grid.showBacktracking) {
                            grid.updateGuiCell(String.valueOf(k), j, i, false);
                        }
                        if (k == 9) {
                            board[i][j] = '.';
                            if (grid.showBacktracking) {
                                grid.updateGuiCell("", j, i, true);
                            }
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
        grid.solverFinished(grid);
        return true;
    }

    public boolean boardCheck(int numIndex, int rowIndex){

        // iterate over both the vertical and horizontal and check if it is unique.
        int xCounter = 0;
        int yCounter = 0;

        for (char digit : board[rowIndex]){ // Checking if number is unique in row.
            if (digit == board[rowIndex][numIndex]){
                xCounter++;
                if (xCounter > 1){
                    return false;
                }
            }
        }

        for (int i = 0; i <= 8; i++){  // Checking if number is unique in column.
            if (board[i][numIndex] == board[rowIndex][numIndex]){
                yCounter++;
                if (yCounter > 1){
                    return false;
                }
            }
        }

        int cornerVertIndex = rowIndex - (rowIndex % 3);
        int cornerHoriIndex = numIndex - (numIndex % 3);
        ArrayList<Character> square = new ArrayList<>();

        for (int i = cornerVertIndex; i < cornerVertIndex + 3; i++){
            for (int j = cornerHoriIndex; j < cornerHoriIndex + 3; j++){
                if (board[i][j] != '.'){
                    square.add(board[i][j]);
                }
            }
        }
        return (square.size() == new HashSet<>(square).size());
    }
}
