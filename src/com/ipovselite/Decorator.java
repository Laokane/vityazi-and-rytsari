package com.ipovselite;

import java.util.Random;


public class Decorator extends Unit {

    protected Unit unit;

    public Decorator(Unit unit) {
        this.unit = unit;
    }

    public static Unit getRandomDecorator(Unit unit) {
        int value = new Random().nextInt(2);
        if (value == 0) {
            return new SwordDecorator(unit);
        } else {
            return new ShieldDecorator(unit);
        }
    }

    public void addItem(Buff buff) {
        this.unit.addItem(buff);
    }

    public Unit getUnit() {
        return unit;
    }
}
