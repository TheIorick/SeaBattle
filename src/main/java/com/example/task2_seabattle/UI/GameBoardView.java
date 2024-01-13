package com.example.task2_seabattle.UI;

import com.example.task2_seabattle.field.Field;
import javafx.scene.layout.GridPane;

public class GameBoardView extends GridPane {

    public GameBoardView(Field gameBoard) {

        int numRows = 10;
        int numCols = 10;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                CellView cellView = new CellView(gameBoard.getStateCell(row, col));
                add(cellView, col, row);
            }
        }
    }
}