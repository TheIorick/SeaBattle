package com.example.task2_seabattle.field;

import com.example.task2_seabattle.ship.Ship;

public class Cell {

    public StateCell stateCell;
    public Ship ElementInCell;
    public boolean shot;
    public int x, y;

    public Cell(int x, int y) {
        this.stateCell = StateCell.WATER;
        this.shot = false;
        this.x = x;
        this.y = y;
    }
}
