package com.example.task2_seabattle;

import com.example.task2_seabattle.field.Field;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    static Field field = new Field();
    public static void main(String[] args) {
//        launch();

        field.draw();
        for (int i = 0; i < 10; i+= 2){
            for (int j = 0; j < 10; j += 2){
                field.doShot(i, j);
            }
        }
        System.out.println();
        field.draw();
    }
}