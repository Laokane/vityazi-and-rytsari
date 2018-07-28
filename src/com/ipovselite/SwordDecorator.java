package com.ipovselite;


public class SwordDecorator extends Decorator {

    public SwordDecorator(Unit unit) {
        super(unit);

    }

    public Unit getUnit() {
        this.unit.addItem(new Sword());
        if (unit instanceof Decorator) {
            return ((Decorator) unit).getUnit();
        }

        return unit;
    }
}
