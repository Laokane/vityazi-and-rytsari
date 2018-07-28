package com.ipovselite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameContext {
    private static GameContext instance;
    private List<Unit> friends = new ArrayList<Unit>();
    private List<Unit> enemies = new ArrayList<Unit>();
    private int countOfUnits;
    private int currentPlayer = 1;
    private int currentUnit;
    private int currentAction;
    private int currentTarget;

    private boolean isInitialized = false;

    public void setCountOfUnits(int countOfUnits) { this.countOfUnits = countOfUnits; }
    public int getCountOfUnits() { return this.countOfUnits; }

    public boolean nextPlayer() {
        if (!enoughPoints()) {
            currentPlayer = currentPlayer == 1 ? 2 : 1;
            //swap friends and enemies as it is other player's step
            List<Unit> temp = friends;
            friends = enemies;
            enemies = temp;
            temp = null;
            return true;
        }

        return false;
    }
    public int getCurrentPlayer() { return currentPlayer; }

    public List<Unit> getFriends() { return friends; }
    public List<Unit> getEnemies() { return enemies; }

    public boolean isInitialized() { return isInitialized; }
    public void clearState() { isInitialized = false; }

    public void setCurrentUnit(int unit) { currentUnit = unit; }
    public boolean checkCurrentUnit() {
        if (currentUnit < 1 || currentUnit > countOfUnits) return false;

        return true;
    }
    public boolean checkCurrentAction() {
        if (currentAction < 0 || currentAction > friends.get(currentUnit - 1).getAvailableActions().size()) return false;
        else
        return true;
    }
    public void setCurrentAction(int action) { currentAction = action; }
    public int getCurrentAction() { return currentAction; }

    public int getCurrentUnit() { return currentUnit; }


    public static void main(String[] args) {
	// write your code here
        //new GameContext().execute();
        new MainFrame().init();
    }

    private GameContext() {}

    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }

        return instance;
    }

    private void execute() {
        //choose number of units
        int countOfUnits = ConsoleUtils.input(4, 10, "Введите количество воинов");

        //initialize(countOfUnits);
        boolean alive = true;
        boolean won = false;
        int player = 0;
        do {
            for (player = 1; player <= 2; player++) {
                //step of one player

                //restore temporary characteristics
                for (Unit friend : friends) {
                    friend.restoreTemporary();
                }

                do {
                    if (!enoughPoints()) break;
                    //show units
                    ConsoleUtils.print("---Вражеские воины---");
                    for (Unit unit : enemies) {
                        ConsoleUtils.print(unit.toString());
                    }
                    ConsoleUtils.print("---Мои воины---");
                    for (int i = 0; i < friends.size(); i++) {
                        ConsoleUtils.print((i+1) + " - " + friends.get(i).toString());
                    }

                    //enter unit id for action
                    boolean isAvailable = false;
                    int id = 0;
                    while (!isAvailable) {
                        id = ConsoleUtils.input(1, friends.size(), "Выбери воина");
                        if (friends.get(id - 1).getPoints() == 0 || friends.get(id - 1).getHealth() == 0 || friends.get(id - 1).isStunned()) {
                            continue;
                        } else {
                            isAvailable = true;
                        }
                    }
                    //show available actions
                    List<Action> actions = friends.get(id - 1).getAvailableActions();
                    ConsoleUtils.print("\n\n---Доступные действия---\n");
                    ConsoleUtils.print("0 - Пропустить");
                    for (int i = 0; i < actions.size(); i++) {
                        ConsoleUtils.print((i+1) + " - " + actions.get(i).toString());
                    }

                    //choose action
                    int actionId = ConsoleUtils.input(0, actions.size(), "Выбери действие");
                    if (actionId == 0 || actions.size() == 0) {
                        continue;
                    }
                    Action action = friends.get(id - 1).getAvailableActions().get(actionId - 1);

                    //choose target
                    int targetId = 0;

                    //attack or make action
                    if (action.getBaseType().equals(ActionType.ATTACK) || action.getBaseType().equals(ActionType.STUN)) {
                        targetId = ConsoleUtils.input(1, enemies.size(), "Выбери цель для атаки");
                        friends.get(id - 1).act(enemies.get(targetId - 1), action);
                    } else if (action.getBaseType().equals(ActionType.DEFENSE) || action.getBaseType().equals(ActionType.HEAL)) {
                        targetId = ConsoleUtils.input(1, friends.size(), "Выбери цель для помощи");
                        friends.get(id - 1).act(friends.get(targetId - 1), action);
                    }
                    //check alive
                    alive = false;
                    for (Unit unit : friends) {
                        if (unit.getHealth() > 0) {
                            alive = true;
                        } else {
                            unit.setPoints(0);
                        }
                    }
                    //check victory
                    won = true;
                    for (Unit unit : enemies) {
                        if (unit.getHealth() > 0) {
                            won = false;
                        } else {
                            unit.setPoints(0);
                        }
                    }

                } while (alive && !won && enoughPoints());

                if (won) break;
                //swap friends and enemies as it is other player's step
                List<Unit> temp = friends;
                friends = enemies;
                enemies = temp;
                temp = null;
            }
            //loop of steps
        } while (alive && !won);
        ConsoleUtils.print("Победил игрок " + player);

    }

    private boolean enoughPoints() {
        int sum = 0;
        int minPrice = 100;
        for (Unit u : friends) {
            sum += u.getPoints() + u.getMana();
            List<Action> actions = u.getAvailableActions();
            for (Action action : actions) {
                minPrice = Math.min(minPrice, action.getPrice());
            }
        }

        return sum >= minPrice;
    }



    public void initialize() {
        initializeUnits(friends);
        initializeUnits(enemies);
        isInitialized = true;
    }

    private void initializeUnits(List<Unit> units) {
        Random rand = new Random();
        for (int i = 0; i < countOfUnits; i++) {
            int typeChooser = rand.nextInt(3);
            UnitType type = null;
            switch (typeChooser) {
                case 0 : type = UnitType.Knight; break;
                case 1 : type = UnitType.Magician; break;
                case 2 : type = UnitType.Archer; break;
            }

            Unit unit = Unit.getUnit(type);
            //ConsoleUtils.print(unit.toString());
            if (type.equals(UnitType.Knight)) {
                Decorator decorator = new SwordDecorator(Decorator.getRandomDecorator(unit));
                unit = decorator.getUnit();
            } else if (type.equals(UnitType.Magician)) {
                Decorator decorator = new StaffDecorator(new StaffDecorator(unit));
                unit = decorator.getUnit();
            } else if (type.equals(UnitType.Archer)) {
                unit = new BowDecorator(unit).getUnit();
            }
            //ConsoleUtils.print(unit.toString());
            unit.setName(Randomizer.getName());
            units.add(unit);
        }
    }

    public boolean checkVictory() {
        boolean won = true;
        for (Unit unit : enemies) {
            if (unit.getHealth() > 0) {
                won = false;
                break;
            }
        }

        return won;
    }
}
