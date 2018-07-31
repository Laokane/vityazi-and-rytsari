package com.ipovselite;

import java.util.Random;

/**
 * Created by User on 13.06.2016.
 */
public class Buff {
    protected int attackBonus;
    protected int defendBonus;
    protected String name = "пусто";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefendBonus() {
        return defendBonus;
    }
}
