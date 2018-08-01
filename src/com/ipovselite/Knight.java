package com.ipovselite;

/**
 * Created by User on 13.06.2016.
 */
public class Knight extends Unit {

    public Knight() {
        health = 6;
        defaultAttack = 5;
        type = "Рыцарь";
        permission = Permission.Knight;
    }
}
