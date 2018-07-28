package com.ipovselite;


public class StaffDecorator extends Decorator {
    public StaffDecorator(Unit unit) {
        super(unit);
    }

    public Unit getUnit() {
        this.unit.addItem(new Staff());
        if (unit instanceof Decorator) {
            return ((Decorator) unit).getUnit();
        }

        return unit;
    }
}
