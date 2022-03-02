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
            try {
                grid.loadSudokuFromString(textField.getText());
            }
            catch (NumberFormatException e){
                textField.setText("Not a valid format.");
            }
        });

        getChildren().add(textField);
        getChildren().add(submitButton);

    }


}
