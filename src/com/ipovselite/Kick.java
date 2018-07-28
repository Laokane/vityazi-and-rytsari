package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class Kick extends Action {
    public Kick() {
        baseType = ActionType.ATTACK;
        power = ActionPower.MINI;
        price = 1;
        probability = 10;
        permission = Permission.All;
        name = "Удар";
    }
}
