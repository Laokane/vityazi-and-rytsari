package com.ipovselite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by User on 18.06.2016.
 */
public class MainPanel extends JPanel {
    private static final String ENTER_COUNT = "Введите число воинов",
            COUNT_OF_UNITS = "Число воинов",
            FRIENDS = "Мои воины",
            ENEMIES = "Вражеские воины",
            EVENTS = "События",
            ACTIONS = "Доступные действия",
            INFO = "Инфо",
            STEP = "Ход",
            UNIT = "Воин",
            ACTION = "Действие",
            TARGET = "Цель",
            ACCEPT = "Принять";
    JLabel lenterCount, lfriends, lenemies, levents, lactions, linfo, lstep, lunit, laction, ltarget;
    JTextField tenterCount, tunit, taction, ttarget;
    JTextArea tfriends, tenemies, tevents, tactions, tinfo;
    JButton benterCount, bunit, baction, btarget;
    JPanel penterCount, pevents, pfriends, penemies, pactions, pinfo, pstep, penterCount_events, ptop, pbottom;

    int screenWidth, screenHeight;

    public MainPanel() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        lenterCount = new JLabel(ENTER_COUNT);
        lfriends = new JLabel(FRIENDS);
        lenemies = new JLabel(ENEMIES);
        levents = new JLabel(EVENTS);
        lactions = new JLabel(ACTIONS);
        linfo = new JLabel(INFO);
        lstep = new JLabel(STEP);
        lunit = new JLabel(UNIT);
        laction = new JLabel(ACTION);
        ltarget = new JLabel(TARGET);

        tenterCount = new JTextField();
        tunit = new JTextField();
        taction = new JTextField();
        ttarget = new JTextField();
        tfriends = new JTextArea();
        tenemies = new JTextArea();
        tevents = new JTextArea(20, 1);
        tactions = new JTextArea(10,1);
        tinfo = new JTextArea(10,1);
        benterCount = new JButton(ACCEPT);
        bunit = new JButton(ACCEPT);
        baction = new JButton(ACCEPT);
        btarget = new JButton(ACCEPT);

        penterCount = new JPanel();
        pevents = new JPanel();
        pfriends = new JPanel();
        penemies = new JPanel();
        pactions = new JPanel();
        pinfo = new JPanel();
        pstep = new JPanel();
        penterCount_events = new JPanel();
        ptop = new JPanel();
        pbottom = new JPanel();
        UserInterface.setOutput(tevents);
        addListeners();
        layoutChildren();
    }

    private void addListeners() {
        benterCount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int countOfUnits = 0;
                boolean ok = true;
                try {
                    countOfUnits = UserInterface.input(4, 10, tenterCount, tevents, COUNT_OF_UNITS);
                } catch (InputValidatorException e1) {
                    ok = false;
                }

                if (ok) {
                    GameContext context = GameContext.getInstance();
                    context.setCountOfUnits(countOfUnits);
                    context.initialize();
                    tevents.append("\nЧисло воинов - " + countOfUnits + "\nХодит игрок " + context.getCurrentPlayer());
                    benterCount.setEnabled(false);
                    refreshUnitState();
                }

            }
        });

        bunit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameContext context = GameContext.getInstance();
                if (!context.isInitialized()) {
                    tevents.append("\nВведите число воинов!");
                } else {
                    int unit = 0;
                    boolean ok = true;
                    try {
                        unit = UserInterface.input(1, context.getCountOfUnits(), tunit, tevents, UNIT);
                    } catch (InputValidatorException ive) {
                        ok = false;
                        context.setCurrentUnit(0);
                    }
                    if (ok) {
                        Unit selectedUnit = context.getFriends().get(unit - 1);
                        if (selectedUnit.isAvailable()) {
                            context.setCurrentUnit(unit);
                            tinfo.setText(context.getFriends().get(unit - 1).toString());
                            tinfo.append("\nВыбранное действие: нет");
                            List<Action> actions = context.getFriends().get(unit - 1).getAvailableActions();
                            tactions.setText("");
                            tactions.append("\n0 - Пропустить ход");
                            int i = 1;
                            for (Action a : actions) {
                                tactions.append("\n" + i++ + " - " + a.toString());
                            }
                        } else {
                            tevents.append("\nВоин " + selectedUnit.getName() + " недоступен!");
                            context.setCurrentUnit(0);
                        }

                    }
                }
            }
        });

        baction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameContext context = GameContext.getInstance();
                if (!context.isInitialized()) {
                    tevents.append("\nВведите число воинов!");
                } else {
                    if (!context.checkCurrentUnit()) {
                        tevents.append("\nВыберите воина!");
                        context.setCurrentAction(-1);
                    } else {
                        int action = 0;
                        boolean ok = true;
                        try {
                            action = UserInterface.input(0, context.getFriends().get(context.getCurrentUnit() - 1).getAvailableActions().size(),
                                    taction, tevents, ACTION);
                        } catch (InputValidatorException ive) {
                            ok = false;
                            context.setCurrentAction(-1);
                        }

                        if (ok) {
                            context.setCurrentAction(action);
                            Unit actor = context.getFriends().get(context.getCurrentUnit() - 1);
                            if (action == 0) {
                                actor.setPoints(0);
                                clearCurrentParameters();
                                refreshUnitState();
                                UserInterface.print("Воин " + actor.getName() + " пропустил ход");
                                if (context.nextPlayer()) {
                                    tevents.append("\nХодит игрок " + context.getCurrentPlayer());
                                    refreshUnitState();
                                    clearCurrentParameters();
                                }
                            } else {
                                String[] lines = tinfo.getText().split("\n");
                                tinfo.setText("");
                                for (int i = 0; i < lines.length - 1; i++) {
                                    tinfo.append(lines[i] + "\n");
                                }
                                tinfo.append("Выбранное действие: " + actor.getAvailableActions().get(action - 1).getName());
                            }
                        }
                    }

                }
            }
        });

        btarget.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameContext context = GameContext.getInstance();
                if (!context.isInitialized()) {
                    tevents.append("\nВведите число воинов!");
                } else {
                    if (!context.checkCurrentUnit()) {
                        tevents.append("\nВыберите воина!");
                    } else {
                        if (!context.checkCurrentAction()) {
                            tevents.append("\nВыберите действие!");
                        } else {
                            int target = 0;
                            boolean ok = true;

                            try {
                                target = UserInterface.input(1, context.getCountOfUnits(), ttarget, tevents, TARGET);
                            } catch (InputValidatorException ive) {
                                ok = false;
                            }
                            if (ok) {
                                Unit actor = context.getFriends().get(context.getCurrentUnit() - 1);
                                Action action = actor.getAvailableActions().get(context.getCurrentAction() - 1);
                                Unit acted = null;
                                if (action.getBaseType().equals(ActionType.ATTACK) || action.getBaseType().equals(ActionType.STUN)) {
                                    acted = context.getEnemies().get(target - 1);
                                } else {
                                    acted = context.getFriends().get(target - 1);
                                }
                                actor.act(acted, action);
                                //refresh
                                refreshUnitState();
                                clearCurrentParameters();
                                if (context.checkVictory()) {
                                    tevents.append("\nПобедил игрок " + context.getCurrentPlayer());
                                    context.clearState();
                                    benterCount.setEnabled(true);
                                } else {
                                    if (context.nextPlayer()) {
                                        tevents.append("\nХодит игрок " + context.getCurrentPlayer());
                                        refreshUnitState();
                                        clearCurrentParameters();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });




    }

    private void clearCurrentParameters() {
        GameContext context = GameContext.getInstance();
        context.setCurrentAction(-1);
        context.setCurrentUnit(0);
        tunit.setText("");
        taction.setText("");
        tinfo.setText("");
        ttarget.setText("");
        tactions.setText("");
    }

    private void layoutChildren() {
        penterCount.setSize(new Dimension((int)0.2 * screenWidth, (int)0.2 * screenHeight));
        penterCount.setLayout(new BoxLayout(penterCount, BoxLayout.Y_AXIS));
        penterCount.add(lenterCount);
        penterCount.add(tenterCount);
        penterCount.add(benterCount);
        penterCount.setBorder(BorderFactory.createLineBorder(Color.black));

        pevents.setSize(new Dimension((int)0.2 * screenWidth, (int)0.4 * screenHeight));
        pevents.setLayout(new BoxLayout(pevents, BoxLayout.Y_AXIS));
        pevents.add(levents);
        //tevents.setMinimumSize(new Dimension((int)0.2 * screenWidth, (int)0.3 * screenHeight));
        tevents.setEditable(false);
        JScrollPane scroll = new JScrollPane(tevents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //scroll.setPreferredSize(new Dimension((int)0.2 * screenWidth, (int)0.3 * screenHeight));
        pevents.add(scroll);
        pevents.setBorder(BorderFactory.createLineBorder(Color.black));

        penterCount_events.setSize(new Dimension((int)0.2 * screenWidth, (int)0.6 * screenHeight));
        penterCount_events.setLayout(new BoxLayout(penterCount_events, BoxLayout.Y_AXIS));
        penterCount_events.add(penterCount);
        penterCount_events.add(pevents);
        /*penterCount_events.add(lenterCount);
        penterCount_events.add(tenterCount);
        penterCount_events.add(benterCount);
        penterCount_events.add(levents);
        penterCount_events.add(tevents);*/

        tfriends.setEditable(false);
        pfriends.setSize(new Dimension((int)0.4 * screenWidth, (int)0.6 * screenHeight));
        pfriends.setLayout(new BoxLayout(pfriends, BoxLayout.Y_AXIS));
        pfriends.add(lfriends);
        pfriends.add(tfriends);
        pfriends.setBorder(BorderFactory.createLineBorder(Color.black));

        tenemies.setEditable(false);
        penemies.setSize(new Dimension((int)0.4 * screenWidth, (int)0.6 * screenHeight));
        penemies.setLayout(new BoxLayout(penemies, BoxLayout.Y_AXIS));
        penemies.add(lenemies);
        penemies.add(tenemies);
        penemies.setBorder(BorderFactory.createLineBorder(Color.black));

        ptop.setSize(new Dimension((int)screenWidth, (int)0.6 * screenHeight));
        ptop.setLayout(new BoxLayout(ptop, BoxLayout.X_AXIS));
        ptop.add(penterCount_events);
        ptop.add(pfriends);
        ptop.add(penemies);

        tactions.setEditable(false);
        pactions.setSize(new Dimension((int)0.4 * screenWidth, (int)0.4 * screenHeight));
        pactions.setLayout(new BoxLayout(pactions, BoxLayout.Y_AXIS));
        pactions.add(lactions);
        pactions.add(tactions);
        pactions.setBorder(BorderFactory.createLineBorder(Color.black));

        tinfo.setEditable(false);
        pinfo.setSize(new Dimension((int)0.2 * screenWidth, (int)0.4 * screenHeight));
        pinfo.setLayout(new BoxLayout(pinfo, BoxLayout.Y_AXIS));
        pinfo.add(linfo);
        pinfo.add(tinfo);
        pinfo.setBorder(BorderFactory.createLineBorder(Color.black));

        pstep.setSize(new Dimension((int)0.4 * screenWidth, (int)0.4 * screenHeight));
        pstep.setLayout(new GridLayout(3,3));
        pstep.add(lunit);
        pstep.add(tunit);
        pstep.add(bunit);
        pstep.add(laction);
        pstep.add(taction);
        pstep.add(baction);
        pstep.add(ltarget);
        pstep.add(ttarget);
        pstep.add(btarget);
        pstep.setBorder(BorderFactory.createLineBorder(Color.black));

        pbottom.setSize(new Dimension((int)screenWidth, (int)0.4 * screenHeight));
        pbottom.setLayout(new BoxLayout(pbottom, BoxLayout.X_AXIS));
        pbottom.add(pactions);
        pbottom.add(pinfo);
        pbottom.add(pstep);

        setSize(new Dimension(screenWidth, screenHeight));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(ptop);
        add(pbottom);
    }

    private void refreshUnitState() {
        GameContext context = GameContext.getInstance();
        tfriends.setText("");
        int i = 1;
        List<Unit> friends = context.getFriends();
        for (Unit unit : friends) {
            tfriends.append("\n" + i++ + " - " + unit.toShortString());
        }
        tenemies.setText("");
        i = 1;
        List<Unit> enemies = context.getEnemies();
        for (Unit unit : enemies) {
            tenemies.append("\n" + i++ + " - " + unit.toShortString());
        }
    }
}
