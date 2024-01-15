package com.example.task2_seabattle.ship.triggerState;

import com.example.task2_seabattle.enumsState.StateCell;
import com.example.task2_seabattle.ship.Ship;


public class TriggerStateShipCheck extends TriggerStateShip {
    public TriggerStateShipCheck(Ship ship) {
        super(ship);
    }

    @Override
    public boolean ship(int n, int m) {
        StateCell stateCell = field.getStateCell(n, m);
        return (stateCell == StateCell.WATER);
    }

    @Override
    public boolean border(int n, int m) {
        StateCell stateCell = field.getStateCell(n, m);
        return (stateCell == StateCell.BORDER) || (stateCell == StateCell.WATER) || (stateCell == StateCell.EMPTY);
    }
}
