package sudokusolverjavafx;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        SudokuGrid sudokuGrid = new SudokuGrid();
        HBox hbox = new HBox(sudokuGrid, new ControlInterface(sudokuGrid));
        Scene scene = new Scene(new VBox(hbox, new TextfieldSudokuLoader(sudokuGrid)));
        stage.setTitle("Sudoku Solver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}