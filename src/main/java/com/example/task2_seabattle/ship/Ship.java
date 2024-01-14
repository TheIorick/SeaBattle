package com.example.task2_seabattle.ship;

import com.example.task2_seabattle.UI.TypeShipUI;
import com.example.task2_seabattle.field.Cell;
import com.example.task2_seabattle.field.Field;
import com.example.task2_seabattle.ship.triggerState.TriggerStateShip;
import com.example.task2_seabattle.ship.triggerState.TriggerStateShipCheck;
import com.example.task2_seabattle.ship.triggerState.TriggerStateShipSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ship {
    public int x, y;
    public TypeShipUI typeShipUI;

    //направление, dy - по вертикали, dx - горизонталь
    private int dx, dy;
    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
    private int healthPoints;
    public boolean shipGhost = false;

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    private int sizeShip;

    public ShipState getShipHealthState() {
        return shipHealthState;
    }

    private ShipState shipHealthState;

    public void setShipHealthState(ShipState shipHealthState) {
        this.shipHealthState = shipHealthState;
    }

    private Field field;
    private List<Cell> cellsShip;
    private List<Cell> cellsBorder;

    public Field getField() {
        return field;
    }

    public List<Cell> getCellsShip() {
        return cellsShip;
    }

    public List<Cell> getCellsBorder() {
        return cellsBorder;
    }

    public Ship(Field field, int sizeShip) {
        switch (sizeShip){
            case 1 -> typeShipUI = TypeShipUI.SHIP1;
            case 2 -> typeShipUI = TypeShipUI.SHIP2;
            case 3 -> typeShipUI = TypeShipUI.SHIP3;
            case 4 -> typeShipUI = TypeShipUI.SHIP4;
        }
        this.sizeShip = sizeShip;
        this.healthPoints = sizeShip;
        this.field = field;
        this.shipHealthState = ShipState.HEALTHY;
        do {
            this.getPlace();
        } while (this.checkPlace());
        this.cellsShip = new ArrayList<>();
        this.cellsBorder = new ArrayList<>();
        this.setShip();
    }
    public Ship (Field field, TypeShipUI state){
        switch (state){
            case SUBMARINE -> {
                this.sizeShip = 3;
                this.healthPoints = 1;
                this.field = field;
                this.shipHealthState = ShipState.SUBMARINE;
            }
            case MINE -> {
                this.sizeShip = 1;
                this.healthPoints = 1;
                this.field = field;
                this.shipHealthState = ShipState.MINE;
            }
            case MINE_SEARCHER -> {
                this.sizeShip = 1;
                this.healthPoints = 1;
                this.field = field;
                this.shipHealthState = ShipState.MINE_SEARCHER;
            }
        }
        do {
            this.getPlace();
        } while (this.checkPlace());
        this.typeShipUI = state;
        this.cellsShip = new ArrayList<>();
        this.cellsBorder = new ArrayList<>();
        this.setShip();
    }

    public Ship(Field field, TypeShipUI state, int sizeShip, int x, int y, boolean directionX) {
        this.typeShipUI = state;
        switch (state){
            case SUBMARINE -> {
                this.sizeShip = 3;
                this.healthPoints = 1;
                this.field = field;
                this.shipHealthState = ShipState.SUBMARINE;
            }
            case MINE -> {
                this.sizeShip = 1;
                this.healthPoints = 1;
                this.field = field;
                this.shipHealthState = ShipState.MINE;
            }
            case MINE_SEARCHER -> {
                this.sizeShip = 1;
                this.healthPoints = 1;
                this.field = field;
                this.shipHealthState = ShipState.MINE_SEARCHER;
            }
            case SHIP1, SHIP2, SHIP3, SHIP4 -> {
                this.sizeShip = sizeShip;
                this.healthPoints = sizeShip;
                this.field = field;
                this.shipHealthState = ShipState.HEALTHY;
            }
        }
        this.x = x;
        this.y = y;
        if(directionX){
            this.dx = 1;
        } else{
            this.dy = 1;
        }
        if (this.checkPlace()){
            shipGhost = true;
            return;
        }
        this.cellsShip = new ArrayList<>();
        this.cellsBorder = new ArrayList<>();
        this.setShip();
    }



    /**
     * Случайное положение корабля на карте
     */
    private void getPlace() {
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
    public boolean checkPlace() {
        return !bypass(new TriggerStateShipCheck(this));
    }

    /**
     * Обертка для установки на поле корабля и его окружения
     */
    private void setShip() {
        bypass(new TriggerStateShipSet(this));
    }

    /**
     * Метод для проверки корректности расположения корабля и его окружения
     */
    private boolean bypass(TriggerStateShip ts) {
        int i, n, m;

        // корабль
        for (i = 0; i < sizeShip; i++) {
            m = y + i * dy;
            n = x + i * dx;
            if (!ts.ship(m, n)) {
                return false;
            }
        }
        // Проверка для бортов корабля
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
        // Проверка на нос и корму
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
