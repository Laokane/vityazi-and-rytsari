package com.ipovselite;

import java.util.Random;

/**
 * Created by User on 13.06.2016.
 */
public class Item {
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

    public void setAttackBonus(int attackBonus) {
        this.attackBonus = attackBonus;
    }

    public int getDefendBonus() {
        return defendBonus;
    }

    public void setDefendBonus(int defendBonus) {
        this.defendBonus = defendBonus;
    }

    public static Item getRandomItem() {
        Random rand = new Random();
        int itemChooser = rand.nextInt(2);
        Item item = null;
        switch (itemChooser) {
            case 0 : item = new Sword();
            case 1 : item = new Shield();
        }

        return item;
    }


}
