package com.example.task2_seabattle.ship.triggerState;

import com.example.task2_seabattle.field.StateCell;
import com.example.task2_seabattle.ship.Ship;

public class TriggerStateShipSet extends TriggerStateShip {
    private final Ship ship;
    public TriggerStateShipSet(Ship ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean Ship(int n, int m) {
        field.setStateCell(n, m, StateCell.HEALTHY);
        ship.getCellsShip().add(field.cells[n][m]);
        field.cells[n][m].elementInCell = ship;
        return true;
    }

    @Override
    public boolean border(int n, int m) {
        field.setStateCell(n, m, StateCell.BORDER);
        if(field.isBound(n, m)) {
            ship.getCellsBorder().add(field.cells[n][m]);
        }
        return true;
    }
}
