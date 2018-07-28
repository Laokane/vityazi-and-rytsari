package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class Stun extends Action {
    public Stun() {
        baseType = ActionType.STUN;
        power = ActionPower.SPECIAL;
        price = 2;
        probability = 6;
        permission = Permission.Knight;
        name = "Оглушение";
    }
}
