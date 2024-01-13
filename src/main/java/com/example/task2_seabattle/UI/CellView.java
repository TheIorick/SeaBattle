package com.example.task2_seabattle.UI;

import com.example.task2_seabattle.field.StateCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CellView extends StackPane {

    public CellView(StateCell state) {
        setPrefSize(50, 50); // Установка размера клетки

        Rectangle border = new Rectangle(30, 30);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.BLACK);

        getChildren().add(border);

        if (state == StateCell.HEALTHY) {
            // Отображение корабля
            Circle ship = new Circle(10, Color.DARKGRAY);
            getChildren().add(ship);
        } else if (state == StateCell.SHOT) {
            // Отображение попадания
            Rectangle hitMark = new Rectangle(50, 50, Color.RED);
            getChildren().add(hitMark);
        }
        // Другие случаи отображения состояний клеток
    }
}
