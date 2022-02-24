package sudokusolverjavafx;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlInterface extends VBox {

    public ControlInterface(SudokuGrid grid){

        Button solveButton = new Button("Solve");
        Button clearButton = new Button("Clear Board");
        Button exampleSudokuButton = new Button("Example Sudoku");

        solveButton.setOnAction(event -> {

            grid.loadSudoku(new SudokuSolver().solveSudoku(grid.convertSudokuToChar(grid.textfieldArr))); // pass the textfield arr

        });

        clearButton.setOnAction(event -> {
            grid.loadSudoku(SudokuSolver.board4);
        });

        exampleSudokuButton.setOnAction(event -> {
            grid.loadSudoku(SudokuSolver.board1);
        });

        getChildren().add(solveButton);
        getChildren().add(clearButton);
        getChildren().add(exampleSudokuButton);

    }

}
