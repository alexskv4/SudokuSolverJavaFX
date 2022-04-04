package sudokusolverjavafx;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ControlInterface extends VBox {

    public Button solveButton;
    public Button clearButton;
    public Button exampleSudokuButton1;
    public Button exampleSudokuButton2; // todo: make these private
    public Button runningNumbersButton;


    public ControlInterface(){

        solveButton = new Button("Solve");
        clearButton = new Button("Clear Board");
        exampleSudokuButton1 = new Button("Example Sudoku 1");
        exampleSudokuButton2 = new Button("Example Sudoku 2");
        runningNumbersButton = new Button ("Running numbers");

//        solveButton.setOnAction(event -> grid.solveSudoku());  // todo : Put button action definitions back here from sudoku grid
//        clearButton.setOnAction(event -> grid.loadSudoku(SudokuSolver.board4));
//        exampleSudokuButton1.setOnAction(event -> grid.loadSudoku(SudokuSolver.board1));
//        exampleSudokuButton2.setOnAction(event -> grid.loadSudoku(SudokuSolver.board2));
//        runningNumbersButton.setOnAction(event -> grid.runningNumbers());

        getChildren().add(runningNumbersButton);
        getChildren().add(solveButton);
        getChildren().add(clearButton);
        getChildren().add(exampleSudokuButton1);
        getChildren().add(exampleSudokuButton2);

    }
    
    public void disableAll (SudokuGrid grid) {

        if (!grid.isSolverStopped){

            solveButton.setText("Stop");
            solveButton.setOnAction(event -> {
                grid.isSolverStopped = true;
                grid.clearBoard();
                disableAll(grid);

            });
            clearButton.setDisable(true);
            exampleSudokuButton1.setDisable(true);
            exampleSudokuButton2.setDisable(true);
            runningNumbersButton.setDisable(true);
        }
        else {

            solveButton.setText("Solve");
            solveButton.setOnAction(event -> {
                //disableAll(grid);
                grid.solveSudoku();
            });
            clearButton.setDisable(false);
            exampleSudokuButton1.setDisable(false);
            exampleSudokuButton2.setDisable(false);
            runningNumbersButton.setDisable(false);

        }
        
    }

}
