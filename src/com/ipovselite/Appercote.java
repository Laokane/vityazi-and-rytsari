package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class Appercote extends Action {
    public Appercote() {
        baseType = ActionType.ATTACK;
        power = ActionPower.NORMAL;
        price = 3;
        probability = 7;
        permission = Permission.Knight;
        name = "Апперкот";
    }
}
