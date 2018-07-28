package com.ipovselite;

import java.util.Random;

/**
 * Created by User on 14.06.2016.
 */
public class Sword extends Item {
    public Sword() {
        attackBonus = new Random().nextInt(3) + 1;
        defendBonus = 0;
        name = "Меч";
    }
}
