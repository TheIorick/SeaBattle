package com.example.task2_seabattle.field;

import com.example.task2_seabattle.enumsState.StateCell;
import com.example.task2_seabattle.ship.Ship;

import java.util.Objects;

public class Cell {
    public StateCell stateCell;
    public Ship elementInCell;
    public boolean shot;
    public int x, y;

    public Cell(int x, int y) {
        this.stateCell = StateCell.WATER;
        this.shot = false;
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return shot == cell.shot && x == cell.x && y == cell.y && stateCell == cell.stateCell && Objects.equals(elementInCell, cell.elementInCell);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateCell, elementInCell, shot, x, y);
    }
}
