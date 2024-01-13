package com.example.task2_seabattle;

import com.example.task2_seabattle.field.Field;
import com.example.task2_seabattle.field.StateCell;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Objects;

import static com.example.task2_seabattle.field.StateCell.WATER;

public class HelloController {
    @FXML
    GridPane panelSettingField = new GridPane();
    Field field;
    @FXML
    private void initialize() {

    }
    private static final int CELL_SIZE = 30;
    public void draw() {
        field = new Field();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {

                CellView cellView = new CellView(field.cells[i][j].stateCell);
                panelSettingField.add(cellView, i + 1, j + 1);
            }
        }
        panelSettingField.requestLayout();
    }
    public static class CellView extends StackPane {

        public CellView(StateCell state) {
            setPrefSize(50, 50); // Установка размера клетки

            Rectangle border = new Rectangle(50, 50);
            border.setFill(Color.LIGHTBLUE);
            border.setStroke(Color.BLACK);

            getChildren().add(border);

            switch (state) {
                case WATER -> border.setFill(Color.LIGHTBLUE);
                case HEALTHY -> {
                    Circle ship = new Circle(25, Color.DARKGRAY);
                    getChildren().add(ship);
                }
            }
            // Другие случаи отображения состояний клеток
        }
    }

}