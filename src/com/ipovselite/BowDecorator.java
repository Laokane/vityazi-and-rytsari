package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class BowDecorator extends Decorator {
    public BowDecorator(Unit unit) {
        super(unit);
    }

    public Unit getUnit() {
        this.unit.addItem(new Bow());
        this.unit.addItem(new Buff());
        if (unit instanceof Decorator) {
            return ((Decorator) unit).getUnit();
        }

        return unit;
    }
}
