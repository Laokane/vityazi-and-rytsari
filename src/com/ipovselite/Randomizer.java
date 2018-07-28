package com.ipovselite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 14.06.2016.
 */
public class Randomizer {
    private static List<Integer> usedNameIds = new ArrayList<Integer>();
    private static Random random = new Random();

    public static boolean getResultByProbability(int probability) {
        int res = random.nextInt(11);
        if (res > probability) {
            return false;
        } else {
            return true;
        }
    }

    public static String getName() {
        String[] names = {"Магомед", "Борзый", "Лилия", "Андал", "Ратимир", "Галим", "Иннокентий", "Урсула", "Корбен", "Талил", "Зева", "Рутор", "Локи", "Охотник", "Зануда", "Каратель", "Седрик"};
        boolean ok = false;
        Random rand = new Random();
        int index = 0;
        while (!ok) {
            index = rand.nextInt(names.length);
            ok = true;
            for (Integer i : usedNameIds) {
                if (index == i) {
                    ok = false;
                }
            }

        }

        return names[index];
    }
}
