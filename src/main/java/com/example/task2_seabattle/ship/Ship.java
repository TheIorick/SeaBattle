package com.example.task2_seabattle.ship;

import com.example.task2_seabattle.field.Cell;
import com.example.task2_seabattle.field.Field;
import com.example.task2_seabattle.ship.triggerState.TriggerStateCell;
import com.example.task2_seabattle.ship.triggerState.TriggerStateCellCheck;
import com.example.task2_seabattle.ship.triggerState.TriggerStateCellSet;

import java.util.ArrayList;
import java.util.Random;

public class Ship {
    private int x, y;
    //направление, dy - по вертикали, dx - горизонталь
    private int dx, dy;
    private int healthPoints;

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    private int sizeShip;
    private ShipState shipHealthState;

    public void setShipHealthState(ShipState shipHealthState) {
        this.shipHealthState = shipHealthState;
    }

    private Field field;
    private ArrayList<Cell> cells;

    public Field getField() {
        return field;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public Ship(Field field, int sizeShip) {
        this.sizeShip = sizeShip;
        this.healthPoints = sizeShip;
        this.field = field;
        this.shipHealthState = ShipState.HEALTHY;
        do {
            this.GetPlace();
        } while (!this.CheckPlace());
        this.cells = new ArrayList<Cell>();
        this.setShip();
    }

    public Ship(Field field, int sizeShip, int x, int y, boolean directionX) {
        this.sizeShip = sizeShip;
        this.healthPoints = sizeShip;
        this.field = field;
        this.shipHealthState = ShipState.HEALTHY;
        this.x = x;
        this.y = y;
        this.dy = 0;
        this.dx = 0;
        if(directionX){
            this.dx = 1;
        } else{
            this.dy = 1;
        }
        this.cells = new ArrayList<Cell>();
        this.setShip();
    }



    /**
     * Случайное положение корабля на карте
     */
    private void GetPlace() {
        Random random = new Random();
        this.x = random.nextInt(10);
        this.y = random.nextInt(10);
        this.dy = 0;
        this.dx = 0;
        if (random.nextInt(2) == 1) {
            this.dx = 1;
        } else {
            this.dy = 1;
        }
    }

    /**
     *   Обертка для проверки на корректность расположения корабля на клетке
     */
    private boolean CheckPlace() {
        return bypass(new TriggerStateCellCheck(this));
    }

    /**
     * Установка на поле корабля и его окружения
     */
    private void setShip() {
        bypass(new TriggerStateCellSet(this));
    }

    /**
     * Метод для проверки корректности расположения корабля и его окружения
     */
    private boolean bypass(TriggerStateCell ts) {
        int i, n, m;

        // корабль
        for (i = 0; i < sizeShip; i++) {
            m = y + i * dy;
            n = x + i * dx;
            if (!ts.Ship(m, n)) {
                return false;
            }
        }
        // площадка сверху и снизу корабля относительно самого корабля
        for (i = 0; i < sizeShip; i++) {
            m = y + i * dy - dx;
            n = x + i * dx - dy;
            if (!ts.border(m, n)) {
                return false;
            }
            m = y + i * dy + dx;
            n = x + i * dx + dy;
            if (!ts.border(m, n)) {
                return false;
            }
        }
        // площадка слева и справа корабля относительно самого корабля
        for (i = -1; i < 2; i++) {
            m = y + i * dx - dy;
            n = x + i * dy - dx;
            if (!ts.border(m, n)) {
                return false;
            }
            m = y + i * dx + dy * sizeShip;
            n = x + i * dy + dx * sizeShip;
            if (!ts.border(m, n)) {
                return false;
            }
        }
        return true;
    }
}
