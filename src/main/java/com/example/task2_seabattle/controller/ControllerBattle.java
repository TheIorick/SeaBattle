package com.example.task2_seabattle.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.task2_seabattle.field.Field;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ControllerBattle {

    Field playerField;

    public void setPlayerField(Field playerField) {
        this.playerField = playerField;
    }

    Field robotField;

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

    }

}
