package sudokusolverjavafx;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;

public class SudokuCell extends Label {

    SudokuCell(){

        getStylesheets().add(Resources.get("SudokuCell.css"));
        setFont(new Font(40));
        setText("");

        setOnKeyPressed(event -> {
            if (event.getCode().isDigitKey()){
                setText(event.getText());
            }
            else if(event.getCode() == KeyCode.BACK_SPACE){
                setText("");
            }

        });

        focusedProperty().addListener((obs, oldVal, newVal) -> setStyle(newVal? "-fx-background-color: orange" : "-fx-background-color: lightblue" ));
        setOnMouseClicked(event -> requestFocus());

    }
}
