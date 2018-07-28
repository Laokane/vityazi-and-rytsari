package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class Shoot extends Action {
    public Shoot() {
        baseType = ActionType.ATTACK;
        power = ActionPower.NORMAL;
        price = 3;
        probability = 7;
        permission = Permission.Archer;
        name = "Выстрел";
    }
}
