package com.ipovselite;

/**
 * Created by User on 13.06.2016.
 */
public class Archer extends Unit {
    public Archer() {
        health = 5;
        currentMinAttack = 1;
        currentMaxAttack = 4;
        type = "Лучник";
        permission = Permission.Archer;
    }
}
