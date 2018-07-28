package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class IronStorm extends Action {
    public IronStorm() {
        baseType = ActionType.ATTACK;
        power = ActionPower.SPECIAL;
        price = 4;
        probability = 6;
        permission = Permission.Knight;
        name = "Железный вихрь";
    }
}
