package com.example.task2_seabattle.field;

import com.example.task2_seabattle.ship.Ship;


import java.util.ArrayList;
import java.util.List;

public class Field {
    final int SIZE_FIELD = 10;
    public Cell[][] cells;
    public List<Ship> ships;

    //todo: сделать изменяемость поля
    public Field() {
        cells = new Cell[SIZE_FIELD][SIZE_FIELD];
        for (int i = 0; i < SIZE_FIELD; i++){
            for (int j = 0; j < SIZE_FIELD; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
        this.putShip();
    }

    /**
     * Заполняем поле кораблями
     */
    //todo: сделать генерацию кораблей с учетом изменяемого поля..
    private void putShip() {
        ships = new ArrayList<Ship>();
        for(int i = 4; i > 0; i--){
            for (int j = (5 - i); j > 0; j--){
                Ship ship = new Ship(this, i);
                ships.add(ship);
            }
        }
//////////////////////////////
    }
    //todo: сделать c учетом изменяемого поля
    public boolean isBound(int x, int y){
        return !((x < 0) || (y < 0) || (x >= SIZE_FIELD) || (y >= SIZE_FIELD));
    }

    /**
     * Получить тип элемента по координатам
     */
    public StateCell getStateCell(int x, int y){
        if (isBound(x, y)){
            return cells[x][y].stateCell;
        } else {
            return StateCell.EMPTY;
        }
    }
    /**
     * Установить тип элемента по координатам
     */
    public boolean setStateCell(int x, int y, StateCell stateCell){
        if (isBound(x, y)){
            cells[x][y].stateCell = stateCell;
        }
        return true;
    }
}
