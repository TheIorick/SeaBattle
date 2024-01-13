package com.example.task2_seabattle;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.task2_seabattle.UI.CellView;
import com.example.task2_seabattle.UI.GameBoardView;
import com.example.task2_seabattle.field.Field;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnRandom;

    @FXML
    private GridPane mainGridPane;

    @FXML
    void draw() {
        Field gameBoard = new Field();
        mainGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        int numRows = 10;
        int numCols = 10;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                CellView cellView = new CellView(gameBoard.getStateCell(row, col));
                mainGridPane.add(cellView, col, row);
            }
        }
    }

    @FXML
    void initialize() {
        assert btnRandom != null : "fx:id=\"btnRandom\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert mainGridPane != null : "fx:id=\"mainGridPane\" was not injected: check your FXML file 'hello-view.fxml'.";
    }

}
