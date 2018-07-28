package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class FireHurricane extends Action {
    public FireHurricane() {
        baseType = ActionType.ATTACK;
        power = ActionPower.SPECIAL;
        price = 3;
        probability = 6;
        permission = Permission.Magician;
        name = "Огненный ураган";
    }
}
