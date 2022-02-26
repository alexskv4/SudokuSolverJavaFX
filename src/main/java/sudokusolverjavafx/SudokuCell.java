package sudokusolverjavafx;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SudokuCell extends Pane {

    SudokuCell(){

        setFocusTraversable(true);
        Label label = new Label("");
        label.setFont(new Font(40));
        getChildren().add(label);
        setMinSize(100.0, 100.0);


        setOnKeyPressed(event -> {
            if (event.getCode().isDigitKey()){
                label.setText(event.getCharacter());
            }

        });
    }


}
