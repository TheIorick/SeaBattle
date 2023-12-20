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
        field.setCellState(n, m, StateCell.HEALTHY);
        ship.getCells().add(field.elements[n][m]);
        field.cells[n][m].ship = ship;
        return true;
    }

    @Override
    public boolean Border(int n, int m) {
        field.setCellState(n, m, StateCell.BORDER);
        return true;
    }
}
