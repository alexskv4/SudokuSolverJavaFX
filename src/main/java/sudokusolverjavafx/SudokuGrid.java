package sudokusolverjavafx;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class SudokuGrid extends GridPane {

    public final TextField[][] textfieldArr = new TextField[9][9];

    public SudokuGrid(){

        getStylesheets().add(Resources.get("SudokuGrid.css")); // Uses our resources class method to get the style from resources folder

        for (int x = 0; x<9; x++){
            for (int y = 0; y<9; y++){
                int finalY = y;
                int finalX = x;
                TextField sudokuCell = new TextField(); // todo Somewhere here make it so that you can only input 1 character into the textField.
                sudokuCell.setOnKeyPressed(event -> {   // todo Tie the textfieldArr to the board in the solve sudoku class to display every step.
                    if (event.getCode() == KeyCode.UP){
                        textfieldArr[wrapGridIndex(finalY - 1)][finalX].requestFocus();
                    }
                    if (event.getCode() == KeyCode.DOWN){
                        textfieldArr[wrapGridIndex(finalY + 1)][finalX].requestFocus();
                    }
                    if (event.getCode() == KeyCode.LEFT){
                        textfieldArr[finalY][wrapGridIndex(finalX - 1)].requestFocus();
                    }
                    if (event.getCode() == KeyCode.RIGHT){
                        textfieldArr[finalY][wrapGridIndex(finalX + 1)].requestFocus();
                    }

                });
                sudokuCell.setFont(new Font(35));
                textfieldArr[y][x] = sudokuCell;
                add(sudokuCell, x, y);
            }
        }

        loadSudoku(SudokuSolver.board4);
    }

    public void setCell(String val, int x, int y){

        textfieldArr[y][x].setText(val);

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

    public char[][] convertSudokuToChar (TextField[][] textfieldArr){

        char[][] sudokuCharArr = new char[9][9];

        for (int x =0; x<9; x++){
            for (int y = 0; y<9; y++){

                String cellString = textfieldArr[y][x].getText();

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
