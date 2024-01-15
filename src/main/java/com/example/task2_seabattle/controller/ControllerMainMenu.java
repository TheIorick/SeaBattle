package com.example.task2_seabattle.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.task2_seabattle.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ControllerMainMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Button btnBattleRobot;

    @FXML
    void goSceneRedactor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("sceneRedactor.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("BebraRedactor");
        stage.setScene(mainScene);
        stage.show();
    }
    @FXML
    void initialize() {

    }

}
