package com.example.task2_seabattle.ship.triggerState;

import com.example.task2_seabattle.field.StateCell;
import com.example.task2_seabattle.ship.Ship;

public class TriggerStateCellSet extends TriggerStateCell{
    private Ship ship;
    public TriggerStateCellSet(Ship ship) {
        super(ship);
        this.ship = ship;
    }

    @Override
    public boolean Ship(int n, int m) {
        field.setStateCell(n, m, StateCell.HEALTHY);
        ship.getCells().add(field.cells[n][m]);
        field.cells[n][m].elementInCell = ship;
        return true;
    }

    @Override
    public boolean border(int n, int m) {
        field.setStateCell(n, m, StateCell.BORDER);
        return true;
    }
}
