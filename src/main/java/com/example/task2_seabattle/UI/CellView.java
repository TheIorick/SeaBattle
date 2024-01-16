package com.example.task2_seabattle.UI;

import com.example.task2_seabattle.field.Cell;
import com.example.task2_seabattle.enumsState.StateCell;
import com.example.task2_seabattle.enumsState.ShipState;
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
                case HEALTHY, SHOT -> {
                    Circle ship = new Circle(10, Color.DARKSLATEGRAY);
                    getChildren().add(ship);
                }
                case MINE_SEARCHER -> {
                    Rectangle mineSearcher = new Rectangle(20, 20, Color.GREENYELLOW);
                    getChildren().add(mineSearcher);
                }
                case MINE -> {
                    Circle mine = new Circle(10, Color.ORANGE);
                    getChildren().add(mine);
                }
                case SUBMARINE -> {
                    Circle submarine = new Circle(10, Color.YELLOW);
                    getChildren().add(submarine);
                }
            }
        } else if (state == StateCell.SHOT || state == StateCell.KILLED) {
            // Отображение попадания
            Circle hitMark = new Circle(10, Color.DARKRED);
            getChildren().add(hitMark);
        } else if (state == StateCell.MISSED) {
            Circle missed = new Circle(10, Color.WHITE);
            getChildren().add(missed);
        }
    }

    public CellView(Cell cell, boolean mineSearcher){
        setPrefSize(30, 30); // Установка размера клетки
        StateCell state = cell.stateCell;
        Rectangle border = new Rectangle(30, 30);
        border.setFill(Color.LIGHTBLUE);
        border.setStroke(Color.BLACK);
        getChildren().add(border);
        if (state == StateCell.SHOT || state == StateCell.KILLED) {
            // Отображение попадания
            Circle hitMark = new Circle(10, Color.DARKRED);
            getChildren().add(hitMark);
        } else if (state == StateCell.MISSED) {
            Circle missed = new Circle(10, Color.WHITE);
            getChildren().add(missed);
        }
    }
}
