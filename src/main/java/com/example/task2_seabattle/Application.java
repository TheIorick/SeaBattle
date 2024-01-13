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

//        Field gameBoard = new Field();
//        GameBoardView gameBoardView = new GameBoardView(gameBoard);
//        Scene scene = new Scene(gameBoardView, 500, 500);
//        stage.setScene(scene);
//        stage.show();
    }
    public static void main(String[] args) {
        launch();

//        field.draw();
//        for (int i = 0; i < 10; i+= 2){
//            for (int j = 0; j < 10; j += 2){
//                field.doShot(i, j);
//            }
//        }
//        System.out.println();
//        field.draw();
    }
}