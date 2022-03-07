package sudokusolverjavafx;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class SudokuCell extends Label {

    SudokuCell(){
        getStylesheets().add(Resources.get("SudokuCell.css"));
        setFont(new Font(40));
        setText("");
        focusedProperty().addListener((obs, oldVal, newVal) -> setStyle(newVal? "-fx-background-color: orange" : "-fx-background-color: white" ));
        setOnMouseClicked(event -> requestFocus());
    }
}
