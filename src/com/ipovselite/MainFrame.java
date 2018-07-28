package com.ipovselite;

import javax.swing.*;

/**
 * Created by User on 18.06.2016.
 */
public class MainFrame extends JFrame {
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel mp = new MainPanel();
        add(mp);
        setSize(mp.getSize());
        setResizable(false);
        setVisible(true);
    }
}
