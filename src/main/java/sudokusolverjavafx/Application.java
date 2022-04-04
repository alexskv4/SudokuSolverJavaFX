package sudokusolverjavafx;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        ControlInterface controlInterface = new ControlInterface();
        SudokuGrid sudokuGrid = new SudokuGrid(controlInterface);
        HBox hbox = new HBox(sudokuGrid, controlInterface);
        Scene scene = new Scene(new VBox(hbox, new TextfieldSudokuLoader(sudokuGrid)));
        stage.setTitle("Sudoku Solver");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}