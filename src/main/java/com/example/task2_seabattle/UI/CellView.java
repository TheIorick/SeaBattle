package com.example.task2_seabattle.UI;

import com.example.task2_seabattle.field.Cell;
import com.example.task2_seabattle.field.StateCell;
import com.example.task2_seabattle.ship.Ship;
import com.example.task2_seabattle.ship.ShipState;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CellView extends StackPane {
    public CellView(){
        setPrefSize(30, 30); // Установка размера клетки
        Rectangle border = new Rectangle(30, 30);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.BLACK);
        getChildren().add(border);
    }

    public CellView(Cell cell) {
        setPrefSize(30, 30); // Установка размера клетки
        StateCell state = cell.stateCell;
        Rectangle border = new Rectangle(30, 30);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.BLACK);
        getChildren().add(border);

        if (state == StateCell.HEALTHY) {
            ShipState shipState = cell.elementInCell.getShipHealthState();
            switch (shipState){
                case HEALTHY -> {
                    Circle ship = new Circle(10, Color.DARKSLATEGRAY);
                    getChildren().add(ship);
                }
                case MINE_SEARCHER -> {
                    Rectangle mineSearcher = new Rectangle(20, 20, Color.GREENYELLOW);
                    getChildren().add(mineSearcher);
                }
                case MINE -> {
                    Circle mine = new Circle(10, Color.DARKRED);
                    getChildren().add(mine);
                }
                case SUBMARINE -> {
                    Circle submarine = new Circle(10, Color.YELLOW);
                    getChildren().add(submarine);
                }
            }
        } else if (state == StateCell.SHOT) {
            // Отображение попадания
            Rectangle hitMark = new Rectangle(50, 50, Color.RED);
            getChildren().add(hitMark);
        }
        // Другие случаи отображения состояний клеток
    }
}
