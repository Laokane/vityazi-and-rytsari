package com.ipovselite;


public class ShieldDecorator extends Decorator {
    public ShieldDecorator(Unit unit) {
        super(unit);
    }

    public Unit getUnit() {
        this.unit.addItem(new Shield());
        if (unit instanceof Decorator) {
            return ((Decorator) unit).getUnit();
        }

        return unit;
    }
}
