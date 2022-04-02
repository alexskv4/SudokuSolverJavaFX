package sudokusolverjavafx;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlInterface extends VBox {

    public ControlInterface(SudokuGrid grid){

        Button solveButton = new Button("Solve");
        Button clearButton = new Button("Clear Board");
        Button exampleSudokuButton1 = new Button("Example Sudoku 1");
        Button exampleSudokuButton2 = new Button("Example Sudoku 2");
        Button runningNumbersButton = new Button ("Running numbers");

        solveButton.setOnAction(event -> grid.loadSudoku(new SudokuSolver().solveSudoku(grid.convertSudokuToChar(grid.cellArr)))); //todo: If the sudoku solver cant solve, make it show an error.
        clearButton.setOnAction(event -> grid.loadSudoku(SudokuSolver.board4));
        exampleSudokuButton1.setOnAction(event -> grid.loadSudoku(SudokuSolver.board1));
        exampleSudokuButton2.setOnAction(event -> grid.loadSudoku(SudokuSolver.board2));
        runningNumbersButton.setOnAction(event -> grid.runningNumbers());

        getChildren().add(runningNumbersButton);
        getChildren().add(solveButton);
        getChildren().add(clearButton);
        getChildren().add(exampleSudokuButton1);
        getChildren().add(exampleSudokuButton2);

    }

}
