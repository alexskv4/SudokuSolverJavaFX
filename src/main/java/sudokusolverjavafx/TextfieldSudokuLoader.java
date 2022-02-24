package sudokusolverjavafx;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class TextfieldSudokuLoader extends HBox {




    public TextfieldSudokuLoader (SudokuGrid grid) {

        TextField textField = new TextField("Enter Sudoku String");
        Button submitButton = new Button("Load");

        submitButton.setOnAction(event -> {
            grid.loadSudokuFromString(textField.getText());
        });

        getChildren().add(textField);
        getChildren().add(submitButton);

    }


}
