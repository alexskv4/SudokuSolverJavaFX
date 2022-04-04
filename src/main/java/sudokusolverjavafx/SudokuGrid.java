package sudokusolverjavafx;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.concurrent.TimeUnit;

public class SudokuGrid extends GridPane {

    public final SudokuCell[][] cellArr = new SudokuCell[9][9];
    private SudokuSolver sudokuSolver;
    public Thread solverThread;
    public ControlInterface controlInterface;
    public boolean isSolverStopped = false;
    public boolean showBacktracking = true;
    public SudokuGrid(ControlInterface controlInterface){
        this.controlInterface = controlInterface;
        sudokuSolver = new SudokuSolver(this);
        controlInterface.initializeButtons(this);
        getStylesheets().add(Resources.get("SudokuGrid.css")); // Uses our resources class method to get the style from resources folder

        for (int x = 0; x<9; x++){
            for (int y = 0; y<9; y++){
                int finalY = y;
                int finalX = x;

                double upBorderWidth = 1.0;
                double rightBorderWidth = 1.0;
                double downBorderWidth = 1.0;
                double leftBorderWidth = 1.0;

                if (finalX % 3 == 2 && finalX != 8){
                    rightBorderWidth = 5.0;
                }
                if (finalY % 3 == 0 && finalY != 0){
                    upBorderWidth = 5.0;
                }

                SudokuCell sudokuCell = new SudokuCell();
                sudokuCell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(upBorderWidth, rightBorderWidth, downBorderWidth, leftBorderWidth))));

                //todo Put all this navigation logic in the sudokuCell class?

                sudokuCell.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.UP){
                        cellArr[wrapGridIndex(finalY - 1)][finalX].requestFocus();
                    }
                    if (event.getCode() == KeyCode.DOWN){
                        cellArr[wrapGridIndex(finalY + 1)][finalX].requestFocus();
                    }
                    if (event.getCode() == KeyCode.LEFT){
                        cellArr[finalY][wrapGridIndex(finalX - 1)].requestFocus();
                    }
                    if (event.getCode() == KeyCode.RIGHT){
                        cellArr[finalY][wrapGridIndex(finalX + 1)].requestFocus();
                    }
                    if (event.getCode().isDigitKey() && event.getCode() != KeyCode.DIGIT0){
                        setCell(event.getText(), finalX, finalY, true); //todo: fix highlighting
                    }
                    if (event.getCode() == KeyCode.BACK_SPACE){
                        setCell("", finalX, finalY, true);
                    }
                    event.consume();
                });
                sudokuCell.setFont(new Font(35));
                cellArr[y][x] = sudokuCell;
                add(sudokuCell, x, y);
            }
        }
        loadSudoku(SudokuBoards.board4); //todo: make static SudokuBoards class and put all the boards there.
    }

    public void setCell(String val, int x, int y, boolean isValid){

        cellArr[y][x].setText(val);
        cellArr[y][x].setTextFill(isValid? Color.BLACK : Color.RED);
    }

    private int wrapGridIndex (int currentIndex) {

        if (currentIndex > 8){
            return 0;
        } else if (currentIndex < 0) {
            return 8;
        } else {
            return currentIndex;
        }
    }

    public void loadSudoku(char[][] sudoku){

        for (int x = 0, len = sudoku.length; x<len; x++){
            for (int y = 0; y<len; y++){

                String cellVal = String.valueOf(sudoku[y][x]);
                if (cellVal.equals(".")){
                    setCell("", x, y, true);
                }
                else {
                    setCell(cellVal, x, y, true);
                    //cellArr[y][x].setStyle("-fx-font-weight: bold"); //todo: Make the numbers bold on loading of a sudoku board
                }
            }
        }
    }

    public void loadSudokuFromString (String sudokuString){

        int i = 0;
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                if (sudokuString.charAt(i) == '0'){
                    setCell("", x, y, true);
                }
                else {
                    setCell(sudokuString.substring(i, i+1), x, y, true);
                }
                i++;
            }
        }
    }

    public char[][] convertSudokuToChar (SudokuCell[][] cellArr){

        char[][] sudokuCharArr = new char[9][9];

        for (int x =0; x<9; x++){
            for (int y = 0; y<9; y++){

                String cellString = cellArr[y][x].getText();

                if (cellString.isBlank()){
                    sudokuCharArr[y][x] = '.';
                }
                else {
                    sudokuCharArr[y][x] = cellString.charAt(0);
                }
            }
        }
        return sudokuCharArr;
    }

    public void solveSudoku () {
        sudokuSolver.setBoard(convertSudokuToChar(cellArr));
        Task<Void> task = new Task<>() {
            @Override
            public Void call() throws Exception {
                sudokuSolver.solve();
                return null;
            }
        };
        isSolverStopped = false;
        solverThread = new Thread(task);
        solverThread.start();
        controlInterface.disableAll(this);

    }

    public void solverFinished (SudokuGrid grid) { //todo: When clearing board mid solve, sometimes garbage numbers are left on the board. Race condition, board cleared before solver fully exits.
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                isSolverStopped = true;
                controlInterface.disableAll(grid);
                if (!showBacktracking){
                    loadSudoku(sudokuSolver.getBoard());
                }
            }
        });
    }

    public void updateGuiCell (String val, int x, int y, boolean isValid) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                setCell(val, x, y, isValid);
            }
        });
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clearBoard() {
        loadSudoku(SudokuBoards.board4);
    }

    public void runningNumbers (){
        Task<Void> task = new Task<>() {
            @Override
            public Void call() throws Exception {
                for (int y = 0; y < 9; y++){
                    for (int x = 0; x < 9; x++){

                        int finalX = x;
                        int finalY = y;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                setCell("1", finalX, finalY, false);
                            }
                        });

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.start();
    }
}
