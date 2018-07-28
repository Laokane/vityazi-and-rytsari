package com.ipovselite;

import java.util.Random;

/**
 * Created by User on 14.06.2016.
 */
public class Shield extends Item {
    public Shield() {
        attackBonus = 0;
        defendBonus = new Random().nextInt(3) + 1;
        name = "Шит";
    }
}
