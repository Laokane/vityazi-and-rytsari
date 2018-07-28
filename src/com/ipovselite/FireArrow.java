package com.ipovselite;

/**
 * Created by User on 14.06.2016.
 */
public class FireArrow extends Action {
    public FireArrow() {
        baseType = ActionType.ATTACK;
        power = ActionPower.SPECIAL;
        price = 4;
        probability = 7;
        permission = Permission.Archer;
        name = "Огненная стрела";
    }
}
