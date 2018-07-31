package com.ipovselite;

import java.util.Random;

/**
 * Created by User on 14.06.2016.
 */
public class Staff extends Buff {
    public Staff() {
        attackBonus = new Random().nextInt(1) + 1;
        defendBonus = new Random().nextInt(2);
        name = "Посох";
    }
}
