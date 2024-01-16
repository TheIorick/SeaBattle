package com.example.task2_seabattle.controller;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.task2_seabattle.Robot;
import com.example.task2_seabattle.UI.CellView;
import com.example.task2_seabattle.enumsState.TypeShipUI;
import com.example.task2_seabattle.field.Field;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import static com.example.task2_seabattle.controller.ControllerRedactor.SIZE_CELL;
import static com.example.task2_seabattle.controller.ControllerRedactor.SIZE_FIELD;

public class ControllerBattle {
    Robot robot;

    private Field playerField;

    Field robotField;
    Random random = new Random();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelCntMine;

    @FXML
    private Label labelCntMineSearcher;

    @FXML
    private Label labelCntShip1;

    @FXML
    private Label labelCntShip2;

    @FXML
    private Label labelCntShip3;

    @FXML
    private Label labelCntShip4;

    @FXML
    private Label labelCntSubmarine;

    @FXML
    private GridPane mainPlayerGridPane;

    @FXML
    private GridPane robotGridPane;

    @FXML
    void mouseRobotClickedEvent(MouseEvent event) {
        int colIndex = (int) (event.getX() / SIZE_CELL); // Определяем индекс столбца
        int rowIndex = (int) (event.getY() / SIZE_CELL); // Определяем индекс строки
        if (robotField.doShot(rowIndex, colIndex)) {
            robotGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
            robotField.shotMinePlayer(playerField, robot, rowIndex, colIndex);
            robotField.shotMineSearcher(rowIndex, colIndex);
            updateView();
            return;
        }
        while (robot.move()){

        };
        updateView();
    }

    private void updateView(){
        for (int row = 0; row < SIZE_FIELD; row++) {
            for (int col = 0; col < SIZE_FIELD; col++) {
                CellView cellView = new CellView(robotField.cells[row][col], true);
                CellView cellViewPlayer = new CellView(playerField.cells[row][col]);
                mainPlayerGridPane.add(cellViewPlayer, col, row);
                robotGridPane.add(cellView, col, row);
            }
        }
    }
    @FXML
    void initialize() {
        assert labelCntMine != null : "fx:id=\"labelCntMine\" was not injected: check your FXML file 'battle.fxml'.";
        assert labelCntMineSearcher != null : "fx:id=\"labelCntMineSearcher\" was not injected: check your FXML file 'battle.fxml'.";
        assert labelCntShip1 != null : "fx:id=\"labelCntShip1\" was not injected: check your FXML file 'battle.fxml'.";
        assert labelCntShip2 != null : "fx:id=\"labelCntShip2\" was not injected: check your FXML file 'battle.fxml'.";
        assert labelCntShip3 != null : "fx:id=\"labelCntShip3\" was not injected: check your FXML file 'battle.fxml'.";
        assert labelCntShip4 != null : "fx:id=\"labelCntShip4\" was not injected: check your FXML file 'battle.fxml'.";
        assert labelCntSubmarine != null : "fx:id=\"labelCntSubmarine\" was not injected: check your FXML file 'battle.fxml'.";
        assert mainPlayerGridPane != null : "fx:id=\"mainPlayerGridPane\" was not injected: check your FXML file 'battle.fxml'.";
        assert robotGridPane != null : "fx:id=\"robotGridPane\" was not injected: check your FXML file 'battle.fxml'.";
        robotField = new Field();
        playerField = ControllerRedactor.gameField;
        mainPlayerGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        updateView();
        robot = new Robot(playerField);
    }

}
