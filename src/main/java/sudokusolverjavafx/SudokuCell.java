package sudokusolverjavafx;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class SudokuCell extends Pane {

    SudokuCell(){

        getStylesheets().add(Resources.get("SudokuCell.css"));

        setFocusTraversable(true);
        Label label = new Label("Placeholder text");
        label.setFont(new Font(40));

        setOnKeyPressed(event -> {
            if (event.getCode().isDigitKey()){
                label.setText(event.getText());
            }

        });

        setOnMouseClicked(event -> requestFocus());

        getChildren().add(label);

    }


}
