package com.ipovselite;

/**
 * Created by User on 13.06.2016.
 */
public class Action {

    protected ActionType baseType;
    protected int price;
    protected int probability;
    protected ActionPower power;
    protected Permission permission;
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public ActionType getBaseType() {
        return baseType;
    }

    public int getPrice() {
        return price;
    }

    public void execute(Unit src, Unit target) {
        String event = "--СОБЫТИЕ: ";
        if (baseType.equals(ActionType.ATTACK)) {
            int attack = src.getCurrentAttack();
            event += "Воин " + src.getName() + " нанес воину " + target.getName() + " " + attack + "ед. урона";
            event += "\n--СОБЫТИЕ: " + target.getName() + " отразил " + target.getDefense() + " eд.урона";
            target.damage(attack);
        } else if (baseType.equals(ActionType.DEFENSE)) {
            event += "Воин " + src.getName() + " защитил воина " + target.getName();
            target.setDefense(target.getDefense() + src.getTotalDefense());
        } else if (baseType.equals(ActionType.HEAL)) {
            event += "Воин " + src.getName() + " полечил воина " + target.getName();
            target.setHealth(target.getHealth() + 5);
        }
        UserInterface.print(event);
        src.setPoints(src.getPoints() - price);
    }

    public String toString() {
        String baseTypeStr = "";
        String powerStr = "";
        if (baseType.equals(ActionType.ATTACK)) {
            baseTypeStr = "Атака";
        } else if (baseType.equals(ActionType.DEFENSE)) {
            baseTypeStr = "Защита";
        } else if (baseType.equals(ActionType.HEAL)) {
            baseTypeStr = "Исцеление";
        } else {
            baseTypeStr = "Оглушение";
        }

        if (power.equals(ActionPower.MINI)) powerStr = "МИНИ-АТАКА";
        if (power.equals(ActionPower.NORMAL)) powerStr = "ОБЫЧНАЯ АТАКА";
        if (power.equals(ActionPower.SPECIAL)) powerStr = "ОСОБОЕ УМЕНИЕ";
        String info = name +  " Тип: " + baseTypeStr + " | Уровень: " + powerStr + " | Вероятность: " + probability * 10 + " %" + " | Цена: " + price + " Эн.";

        return info;
    }
}
