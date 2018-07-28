package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class Lightning extends Action {
    public Lightning() {
        baseType = ActionType.ATTACK;
        power = ActionPower.NORMAL;
        price = 3;
        probability = 8;
        permission = Permission.Magician;
        name = "Разряд молнией";
    }
}
