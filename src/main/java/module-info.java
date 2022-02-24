module com.example.sudokusolverjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens sudokusolverjavafx to javafx.fxml;
    exports sudokusolverjavafx;
}