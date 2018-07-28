package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class Heal extends Action {
    public Heal() {
        baseType = ActionType.HEAL;
        power = ActionPower.SPECIAL;
        price = 2;
        probability = 10;
        permission = Permission.Magician;
        name = "Лечение";
    }
}
