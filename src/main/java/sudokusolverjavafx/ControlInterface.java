package sudokusolverjavafx;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControlInterface extends VBox {

    private Button solveButton;
    private Button clearButton;
    private Button exampleSudokuButton1;
    private Button exampleSudokuButton2;
    private Button runningNumbersButton;
    private CheckBox backtrackingCheckBox;
    private Label iterationCount;

    public ControlInterface(){

        solveButton = new Button("Solve");
        clearButton = new Button("Clear Board");
        exampleSudokuButton1 = new Button("Example Sudoku 1");
        exampleSudokuButton2 = new Button("Example Sudoku 2");
        runningNumbersButton = new Button ("Running numbers");
        backtrackingCheckBox = new CheckBox("Show Backtracking");
        backtrackingCheckBox.selectedProperty().set(true);
        iterationCount = new Label("Iteration Count: 0");

        getChildren().add(runningNumbersButton);
        getChildren().add(solveButton);
        getChildren().add(clearButton);
        getChildren().add(exampleSudokuButton1);
        getChildren().add(exampleSudokuButton2);
        getChildren().add(backtrackingCheckBox);
        getChildren().add(iterationCount);

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
    
    public void initializeButtons (SudokuGrid grid) {
        solveButton.setOnAction(event -> {
            disableAll(grid);
            grid.solveSudoku();
        }); //todo: If the sudoku solver cant solve, make it show an error.
        clearButton.setOnAction(event -> grid.clearBoard());
        exampleSudokuButton1.setOnAction(event -> {
            grid.loadSudoku(SudokuBoards.board1);
            grid.iterationCount = 0;
            grid.updateIterationCounter(grid);  // todo: clean up these lambdas. (move iteration count zeroing to somewhere else.)
        });
        exampleSudokuButton2.setOnAction(event -> {
            grid.loadSudoku(SudokuBoards.board2);
            grid.iterationCount = 0;
            grid.updateIterationCounter(grid);
        });

        runningNumbersButton.setOnAction(event -> grid.runningNumbers());
        backtrackingCheckBox.setOnAction(event -> grid.showBacktracking = backtrackingCheckBox.selectedProperty().get());
        iterationCount.setText("Number of iterations: " + grid.iterationCount);
    }

    public void updateIterationCounter(SudokuGrid grid) {
        iterationCount.setText("Number of iterations: " + grid.iterationCount);
    }
}
