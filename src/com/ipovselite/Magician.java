package com.ipovselite;

/**
 * Created by User on 13.06.2016.
 */
public class Magician extends Unit {
    public Magician() {
        health = 20;
        currentMinAttack = 2;
        currentMaxAttack = 3;
        type = "Маг";
        permission = Permission.Magician;
    }

}
