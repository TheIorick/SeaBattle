package com.example.task2_seabattle.field;

import com.example.task2_seabattle.enumsState.StateOrientation;
import com.example.task2_seabattle.enumsState.TypeShipUI;
import com.example.task2_seabattle.enumsState.StateCell;
import com.example.task2_seabattle.ship.Ship;
import com.example.task2_seabattle.enumsState.ShipState;


import java.util.ArrayList;
import java.util.List;

import static com.example.task2_seabattle.enumsState.TypeShipUI.*;

public class Field {
    final int SIZE_FIELD = 10;
    public Cell[][] cells;
    public List<Ship> ships;

    public Field() {
        cells = new Cell[SIZE_FIELD][SIZE_FIELD];
        for (int i = 0; i < SIZE_FIELD; i++){
            for (int j = 0; j < SIZE_FIELD; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
        this.putShip();
    }

    public Field(boolean empty){
        cells = new Cell[SIZE_FIELD][SIZE_FIELD];
        for (int i = 0; i < SIZE_FIELD; i++){
            for (int j = 0; j < SIZE_FIELD; j++){
                cells[i][j] = new Cell(i, j);
            }
        }
        ships = new ArrayList<Ship>();
    }
    /**
     * Заполняем поле кораблями
     */

    public boolean addShip(TypeShipUI typeShipUI, StateOrientation orientation, int x, int y){
        boolean orient = switch (orientation) {
            case FOR_X -> true;
            case FOR_Y -> false;
        };
        int sizeShip = switch (typeShipUI){
            case SHIP1 -> sizeShip = 1;
            case SHIP2 -> sizeShip = 2;
            case SHIP3 -> sizeShip = 3;
            case SHIP4 -> sizeShip = 4;
            case MINE, SUBMARINE, MINE_SEARCHER -> 0;
        };
        Ship ship = new Ship(this, typeShipUI, sizeShip, x, y, orient);
        if (ship.shipGhost){
            return false;
        }
        ships.add(ship);
        draw();
        return true;
    }

    public TypeShipUI deleteShip(int x, int y){
        for(Ship ship : ships){
            if(ship.x == x && ship.y == y){
                for (Cell cell : ship.getCellsShip()){
                    cell.stateCell = StateCell.WATER;
                }
                for (Cell cell : ship.getCellsBorder()){
                    cell.stateCell = StateCell.WATER;
                }
                TypeShipUI type = ship.typeShipUI;
                ship = null;
                System.out.println();
                draw();
                return type;
            }
        }
        return null;
    }
    private void putShip() {
        ships = new ArrayList<Ship>();
        for(int i = 4; i > 0; i--){
            for (int j = (5 - i); j > 0; j--){
                Ship ship = new Ship(this, i);
                ships.add(ship);
            }
        }
        Ship mine = new Ship(this, MINE);
        ships.add(mine);
        Ship mineSearcher = new Ship(this, MINE_SEARCHER);
        ships.add(mineSearcher);
        Ship submarine = new Ship(this, SUBMARINE);
        ships.add(submarine);
    }
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
    public void setStateCell(int x, int y, StateCell stateCell){
        if (isBound(x, y)){
            cells[x][y].stateCell = stateCell;
        }
    }

    public void doShot(int x, int y){
        boolean shot = false;
        StateCell stateCell = this.getStateCell(x, y);
        cells[x][y].shot = true;
        //Если попали в целую часть корабля
        if (stateCell == StateCell.HEALTHY){
            shot = true;
            Ship ship = cells[x][y].elementInCell;
            if (ship.getHealthPoints() != 0) {
                ship.setHealthPoints(ship.getHealthPoints() - 1);
                if (ship.getHealthPoints() == 0){
                    ship.setShipHealthState(ShipState.KILLED);
                    for (Cell cell : ship.getCellsShip()){
                        cell.stateCell = StateCell.KILLED;
                    }
                    for (Cell cell : ship.getCellsBorder()){
                        cell.stateCell = StateCell.MISSED;
                    }
                } else {
                    ship.setShipHealthState(ShipState.SHOT);
                    cells[x][y].stateCell = StateCell.SHOT;
                }
            }
        //Если промах
        } else {
            if((stateCell == StateCell.BORDER) || (stateCell == StateCell.WATER)){
                this.setStateCell(x, y, StateCell.MISSED);
            }
        }
    }
    //Переименование границ потопленного корабля
//    public boolean checkBorderCell(Ship ship){
//        int n, m, i;
//        for (i = -1; i < 2; i++) {
//            m = y + i * dx - dy;
//            n = x + i * dy - dx;
//            if (stateCell == StateCell.BORDER) {
//                return false;
//            }
//            m = y + i * dx + dy;
//            n = x + i * dy + dx;
//            if (!ts.border(m, n)) {
//                return false;
//            }
//        }
//    }
    public void draw() {
        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                System.out.print(cells[i][j].stateCell.toString() + " ");
            }
            System.out.println();
        }
    }
}
