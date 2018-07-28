package com.ipovselite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 14.06.2016.
 */
public class ActionStore {
    private static List<Action> actionList = new ArrayList<Action>();
    static {
        actionList.add(new Appercote());
        actionList.add(new FireArrow());
        actionList.add(new FireHurricane());
        actionList.add(new Heal());
        actionList.add(new IronStorm());
        actionList.add(new Kick());
        actionList.add(new Lightning());
        actionList.add(new Shoot());
        actionList.add(new Stun());
    }

    public static List<Action> getActionList() { return actionList; }
}
