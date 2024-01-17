package com.example.task2_seabattle.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import com.example.task2_seabattle.Application;
import com.example.task2_seabattle.Robot;
import com.example.task2_seabattle.UI.CellView;
import com.example.task2_seabattle.enumsState.ShipState;
import com.example.task2_seabattle.field.Field;
import com.example.task2_seabattle.ship.Ship;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    private Label labelPlayerCnt1;

    @FXML
    private Label labelPlayerCnt2;

    @FXML
    private Label labelPlayerCnt3;

    @FXML
    private Label labelPlayerCnt4;

    @FXML
    private Label labelPlayerCntMineSearcher;

    @FXML
    private Label labelPlayerCntMine;

    @FXML
    private Label labelPlayerCntSubmarine;

    @FXML
    private Label labelRobotCnt1;

    @FXML
    private Label labelRobotCnt2;

    @FXML
    private Label labelRobotCnt3;

    @FXML
    private Label labelRobotCnt4;

    @FXML
    private Label labelRobotCntMineSearcher;

    @FXML
    private Label labelRobotCntMine;

    @FXML
    private Label labelRobotCntSubmarine;
    private int playerCnt1 = 0;
    private int playerCnt2 = 0;
    private int playerCnt3 = 0;
    private int playerCnt4 = 0;
    private int playerCntMineSearcher = 0;
    private int playerCntMine = 0;
    private int playerCntSubmarine = 0;

    private int robotCnt1 = 0;
    private int robotCnt2 = 0;
    private int robotCnt3 = 0;
    private int robotCnt4 = 0;
    private int robotCntMineSearcher = 0;
    private int robotCntMine = 0;
    private int robotCntSubmarine = 0;

    @FXML
    private GridPane mainPlayerGridPane;

    @FXML
    private GridPane robotGridPane;

    @FXML
    void mouseRobotClickedEvent(MouseEvent event) throws IOException {
        int colIndex = (int) (event.getX() / SIZE_CELL); // Определяем индекс столбца
        int rowIndex = (int) (event.getY() / SIZE_CELL); // Определяем индекс строки
        if (robotField.doShot(rowIndex, colIndex)) {
            robotGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
            robotField.shotMinePlayer(playerField, robot, rowIndex, colIndex);
            robotField.shotMineSearcher(rowIndex, colIndex);
            updateView();
            updateCntAboutShot(robotField, rowIndex, colIndex);
            if (areRobotCountersZero()){
                showMessageWin(event);
            }
            return;
        }
        while (robot.move()){
            updateCntAboutShot(playerField, robot.getX(), robot.getY());
            playerField.shotMineRobot(robotField, robot.getX(), robot.getY());
            playerField.shotMineSearcher(robot.getX(), robot.getY());
            if (arePlayerCountersZero()){
                showMessageLose(event);
            }
            updateView();
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

    private void updateCntAboutShot(Field fieldShot, int rowIndex, int colIndex){
        Ship shipKill = fieldShot.cells[rowIndex][colIndex].elementInCell;
        if (shipKill != null && shipKill.getShipHealthState() == ShipState.KILLED){
            if (fieldShot == playerField){
                switch (shipKill.typeShipUI){
                    case SHIP1 -> {
                        playerCnt1--;
                        labelPlayerCnt1.setText(String.valueOf(playerCnt1));
                    }
                    case SHIP2 -> {
                        playerCnt2--;
                        labelPlayerCnt2.setText(String.valueOf(playerCnt2));
                    }
                    case SHIP3 -> {
                        playerCnt3--;
                        labelPlayerCnt3.setText(String.valueOf(playerCnt3));
                    }
                    case SHIP4 -> {
                        playerCnt4--;
                        labelPlayerCnt4.setText(String.valueOf(playerCnt4));
                    }
                    case MINE_SEARCHER -> {
                        playerCntMineSearcher--;
                        labelPlayerCntMineSearcher.setText(String.valueOf(playerCntMineSearcher));
                        if (playerCntMine > 0){
                            playerCntMine--;
                            labelPlayerCntMine.setText(String.valueOf(playerCntMine));
                        }
                    }
                    case MINE -> {
                        playerCntMine--;
                        labelPlayerCntMine.setText(String.valueOf(playerCntMine));
                    }
                    case SUBMARINE -> {
                        playerCntSubmarine--;
                        labelPlayerCntSubmarine.setText(String.valueOf(playerCntSubmarine));
                    }
                }
            } else{
                switch (shipKill.typeShipUI){
                    case SHIP1 -> {
                        robotCnt1--;
                        labelRobotCnt1.setText(String.valueOf(robotCnt1));
                    }
                    case SHIP2 -> {
                        robotCnt2--;
                        labelRobotCnt2.setText(String.valueOf(robotCnt2));
                    }
                    case SHIP3 -> {
                        robotCnt3--;
                        labelRobotCnt3.setText(String.valueOf(robotCnt3));
                    }
                    case SHIP4 -> {
                        robotCnt4--;
                        labelRobotCnt4.setText(String.valueOf(robotCnt4));
                    }
                    case MINE_SEARCHER -> {
                        robotCntMineSearcher--;
                        labelRobotCntMineSearcher.setText(String.valueOf(robotCntMineSearcher));
                        if (robotCntMine > 0){
                            robotCntMine--;
                            labelRobotCntMine.setText(String.valueOf(robotCntMine));
                        }
                    }
                    case MINE -> {
                        robotCntMine--;
                        labelRobotCntMine.setText(String.valueOf(robotCntMine));
                    }
                    case SUBMARINE -> {
                        robotCntSubmarine--;
                        labelRobotCntSubmarine.setText(String.valueOf(robotCntSubmarine));
                    }
                }

            }
        }
    }

    private void setCntBeginGame(){
        List<Ship> ships = playerField.ships;
        for (Ship ship : ships){
            if (ship.typeShipUI == null){
                continue;
            }
            switch (ship.typeShipUI){
                case SHIP1 -> {
                    playerCnt1++;
                    labelPlayerCnt1.setText(String.valueOf(playerCnt1));
                }
                case SHIP2 -> {
                    playerCnt2++;
                    labelPlayerCnt2.setText(String.valueOf(playerCnt2));
                }
                case SHIP3 -> {
                    playerCnt3++;
                    labelPlayerCnt3.setText(String.valueOf(playerCnt3));
                }
                case SHIP4 -> {
                    playerCnt4++;
                    labelPlayerCnt4.setText(String.valueOf(playerCnt4));
                }
                case MINE_SEARCHER -> {
                    playerCntMineSearcher++;
                    labelPlayerCntMineSearcher.setText(String.valueOf(playerCntMineSearcher));
                }
                case MINE -> {
                    playerCntMine++;
                    labelPlayerCntMine.setText(String.valueOf(playerCntMine));
                }
                case SUBMARINE -> {
                    playerCntSubmarine++;
                    labelPlayerCntSubmarine.setText(String.valueOf(playerCntSubmarine));
                }
            }
        }
        ships = robotField.ships;
        for (Ship ship : ships){
            switch (ship.typeShipUI){
                case SHIP1 -> {
                    robotCnt1++;
                    labelRobotCnt1.setText(String.valueOf(robotCnt1));
                }
                case SHIP2 -> {
                    robotCnt2++;
                    labelRobotCnt2.setText(String.valueOf(robotCnt2));
                }
                case SHIP3 -> {
                    robotCnt3++;
                    labelRobotCnt3.setText(String.valueOf(robotCnt3));
                }
                case SHIP4 -> {
                    robotCnt4++;
                    labelRobotCnt4.setText(String.valueOf(robotCnt4));
                }
                case MINE_SEARCHER -> {
                    robotCntMineSearcher++;
                    labelRobotCntMineSearcher.setText(String.valueOf(robotCntMineSearcher));
                }
                case MINE -> {
                    robotCntMine++;
                    labelRobotCntMine.setText(String.valueOf(robotCntMine));
                }
                case SUBMARINE -> {
                    robotCntSubmarine++;
                    labelRobotCntSubmarine.setText(String.valueOf(robotCntSubmarine));
                }
            }
        }
    }
    private boolean arePlayerCountersZero() {
        return playerCnt1 == 0 && playerCnt2 == 0 && playerCnt3 == 0 && playerCnt4 == 0
                && playerCntMineSearcher == 0 && playerCntSubmarine == 0;
    }
    private boolean areRobotCountersZero() {
        return robotCnt1 == 0 && robotCnt2 == 0 && robotCnt3 == 0 && robotCnt4 == 0
                && robotCntMineSearcher == 0 && robotCntSubmarine == 0;
    }
    private void showMessageWin(Event event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOU WIN!");
        alert.setHeaderText(null);
        alert.setContentText("YOU WIN!");
        alert.showAndWait();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sceneRedactor.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("BebraRedactor");
        stage.setScene(mainScene);
        stage.show();
    }
    private void showMessageLose(Event event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOU LOSE!");
        alert.setHeaderText(null);
        alert.setContentText("YOU LOSE!");
        alert.showAndWait();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sceneRedactor.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("BebraRedactor");
        stage.setScene(mainScene);
        stage.show();
    }
    @FXML
    void initialize() {
        assert mainPlayerGridPane != null : "fx:id=\"mainPlayerGridPane\" was not injected: check your FXML file 'battle.fxml'.";
        assert robotGridPane != null : "fx:id=\"robotGridPane\" was not injected: check your FXML file 'battle.fxml'.";
        robotField = new Field();
        playerField = ControllerRedactor.gameField;
        mainPlayerGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        updateView();
        robot = new Robot(playerField);
        setCntBeginGame();
    }

}
