package com.example.task2_seabattle.ship.triggerState;

import com.example.task2_seabattle.field.Field;
import com.example.task2_seabattle.ship.Ship;

//Абстрактный для проверки и установки значений клетки где находится корабль
public abstract class TriggerStateShip {
    public Field field;

    //приводим все поля к одному виду
    public TriggerStateShip(Ship ship) {
        this.field = ship.getField();
    }

    abstract public boolean Ship(int n, int m);
    abstract public boolean border(int n, int m);
}
