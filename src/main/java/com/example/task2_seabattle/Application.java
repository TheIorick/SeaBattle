package com.example.task2_seabattle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("hello-view.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        stage.setTitle("ЯСобакаТыСобакаЯСобакаТыСобакаЯСобакаТыСобакаЯСобакаТыСобакаЯСобакаТыСобакаЯСобака");
        stage.setScene(mainScene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}