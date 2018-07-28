package com.ipovselite;

/**
 * Created by User on 13.06.2016.
 */
public class Knight extends Unit {

    public Knight() {
        health = 30;
        currentMinAttack = 1;
        currentMaxAttack = 3;
        type = "Рыцарь";
        permission = Permission.Knight;
    }
}
