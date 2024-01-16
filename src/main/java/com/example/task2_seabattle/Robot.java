package com.example.task2_seabattle;

import com.example.task2_seabattle.enumsState.StateCell;
import com.example.task2_seabattle.field.Cell;
import com.example.task2_seabattle.field.Field;

import java.util.ArrayList;
import java.util.Random;

public class Robot {
    private static final int SIZE_FIELD = 10;
    public Field field;
    public int x, y;
    Random random;

    public Robot(Field field) {
        this.field = field;
        this.random = new Random();
    }
    public boolean tryShot() {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for (int j = 0; j < SIZE_FIELD; j++) {
            for (int i = 0; i < SIZE_FIELD; i++) {
                Cell cell = field.cells[i][j];
                if (!cell.shot) {
                    list.add(cell);
                }
            }
        }
        Cell cell = list.get(random.nextInt(list.size()));
        return field.doShot(cell.x, cell.y);
    }
    public boolean move() {
        boolean find = false;
        // ищём раненый элемент корабля
        first: for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                Cell cell = field.cells[i][j];
                if (cell.stateCell == StateCell.SHOT) {
                    // нашли раненый элемент корабля
                    find = true;
                    x = i;
                    y = j;
                    break first;
                }
            }
        }
        if (find) {
            ArrayList<Cell> list = new ArrayList<Cell>();
            // пробуем стрелять вокруг раненого корабля
            // выбрал случайную позицию
            for(int j = -1; j < 2; j += 2) {
                int a = x;
                int b = y + j;
                if ( (b < 0) || (b>9) ) {
                    continue;
                }
                if (!field.cells[a][b].shot) {
                    list.add(field.cells[a][b]);
                }
            }
            for(int i = -1; i < 2; i += 2) {
                int a = x + i;
                int b = y;
                if ( (a < 0) || (a>9) ) {
                    continue;
                }
                if (!field.cells[a][b].shot) {
                    list.add(field.cells[a][b]);
                }
            }
            if (!list.isEmpty()) {
                Cell cell = list.get(random.nextInt(list.size()));
                return field.doShot(cell.x, cell.y);
            }
        }
        return  tryShot();
    }
}
