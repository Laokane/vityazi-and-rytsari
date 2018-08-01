package com.ipovselite;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 13.06.2016.
 */
public class Unit {

    protected int points;
    protected int mana;
    protected int health;
    protected int defense;
    protected int defaultAttack;
    protected boolean stunned;
    protected String type;
    protected List<Buff> buffs;
    protected String name;
    protected Permission permission;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit() {
        buffs = new ArrayList<Buff>(2);
        points = 5;
        defense = getTotalDefense();
        stunned = false;
    }

    public boolean isStunned() {
        return stunned;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void addItem(Buff buff) {
        if (buffs.size() < 2)
            buffs.add(buff);
        restoreTemporary();
    }

    public int getCurrentAttack() {return defaultAttack + buffs.stream().mapToInt(Buff::getAttackBonus).sum();
    }

    public List<Action> getAvailableActions() {
        List<Action> allActions = ActionStore.getActionList();
        List<Action> result = new ArrayList<Action>();
        for (Action a : allActions) {
            if (points >= a.getPrice() && (permission.equals(a.getPermission()) || a.getPermission().equals(Permission.All)) ) {
                result.add(a);
            }
        }

        return result;

    }

    public void act(Unit unit, Action action) {
        action.execute(this, unit);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        if (points < 0) {
            this.points = 0;
        } else {
            this.points = points;
        }
    }

    public int getMana() {
        return mana;
    }

    public static Unit getUnit(UnitType type) {
        Unit unit = null;
        if (type.equals(UnitType.Knight)) {
            unit = new Knight();
        } else if (type.equals(UnitType.Magician)) {
            unit = new Magician();
        } else if (type.equals(UnitType.Archer)) {
            unit = new Archer();
        }

        return unit;
    }

    public void damage(int attack) {
        int diff = defense - attack;
        if (diff < 0) {
            defense = 0;
            health += diff;
        } else {
            defense -= attack;
        }

        if (health < 0)
            health = 0;
    }

    public void restoreTemporary() {
        points = stunned ? 0 : 5;
        defense = getTotalDefense();
        stunned = false;
    }

    public int getTotalDefense() {
        int totalDefense = 0;
        for (int i = 0; i < buffs.size(); i++) {
            totalDefense += buffs.get(i).getDefendBonus();
        }

        return totalDefense;
    }

    public boolean isAvailable() {
        if (points == 0 || health == 0 || stunned) {
            return false;
        } else {
            return true;
        }

    }

    public String toShortString() {
        String available = points == 0 || stunned || health == 0 ? "Недоступен": "Доступен";
        String info = name + " (" + type + ") Зд.: " + health + " Эн.: " + points + " | " + available;

        return info;
    }

    public String toString() {
        String available = points == 0 || stunned || health == 0 ? "Недоступен": "Доступен";
        String info = name + " (" + type + ")\nЗд.: " + health + " Эн.: " + points + "\n" + available;

        return info;
    }
}
