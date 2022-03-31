package sudokusolverjavafx;

import javafx.css.PseudoClass;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;

public class TextfieldSudokuLoader extends HBox {
    public TextfieldSudokuLoader (SudokuGrid grid) {
        getStylesheets().add(Resources.get("TextField.css"));
        TextField textField = new TextField("Enter an 81 digit Sudoku");
        Button submitButton = new Button("Load");

        PseudoClass errorClass = PseudoClass.getPseudoClass("error");

        textField.textProperty().addListener(event -> {
            textField.pseudoClassStateChanged(
                    errorClass,
                    !checkNumberFormat(textField.getText()) && textField.isFocused()
                    );
        });

        textField.focusedProperty().addListener(event -> {
            textField.pseudoClassStateChanged(
                    errorClass,
                    false
                    );
        });

        textField.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ENTER){
                loadSudoku(grid, textField);
            }
        });

        submitButton.setOnAction(event -> {
            loadSudoku(grid, textField);
        });

        getChildren().add(textField);
        getChildren().add(submitButton);
    }

    private void loadSudoku (SudokuGrid grid, TextField textField){
        if (checkNumberFormat(textField.getText())) {
            grid.loadSudokuFromString(textField.getText());
        }
        else {
            textField.setText("Not a valid format.");
        }
    }

    private boolean checkNumberFormat (String sudokuString){

        if (sudokuString.length() != 81){
            return false;
        }

        char[] chars = sudokuString.toCharArray();
        for (char c : chars){
            if (!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
