package sudokusolverjavafx;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SudokuGrid extends GridPane {

    public final SudokuCell[][] cellArr = new SudokuCell[9][9];

    public SudokuGrid(){

        getStylesheets().add(Resources.get("SudokuGrid.css")); // Uses our resources class method to get the style from resources folder

        for (int x = 0; x<9; x++){
            for (int y = 0; y<9; y++){
                int finalY = y;
                int finalX = x;
                SudokuCell sudokuCell = new SudokuCell();
                sudokuCell.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                //todo Make the setBorder set differing borders on different sides depending where in the grid the cell is to make the sudoku 3x3 squares.

                sudokuCell.setOnKeyPressed(event -> {   //todo Tie the cellArr to the board in the solve sudoku class to display every step.
                                                        //todo Put all this navigation logic in the sudokuCell class?
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
                        setCell(event.getText(), finalX, finalY);
                    }
                    if (event.getCode() == KeyCode.BACK_SPACE){
                        setCell("", finalX, finalY);
                    }
                    event.consume();
                });
                sudokuCell.setFont(new Font(35));
                cellArr[y][x] = sudokuCell;
                add(sudokuCell, x, y);
            }
        }
        loadSudoku(SudokuSolver.board4);
    }

    public void setCell(String val, int x, int y){

        cellArr[y][x].setText(val);
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
                    setCell("", x, y);
                }
                else {
                    setCell(cellVal, x, y );
                }
            }
        }
    }

    public void loadSudokuFromString (String sudokuString){

        if (sudokuString.length() != 81){
            throw new NumberFormatException();
        }

        char[] chars = sudokuString.toCharArray();
        for (char c : chars){
            if (!Character.isDigit(c)){
                throw new NumberFormatException();
            }
        }

        int i = 0;
        for (int y = 0; y < 9; y++){
            for (int x = 0; x < 9; x++){
                if (sudokuString.charAt(i) == '0'){
                    setCell("", x, y);
                }
                else {
                    setCell(sudokuString.substring(i, i+1), x, y);
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
}
