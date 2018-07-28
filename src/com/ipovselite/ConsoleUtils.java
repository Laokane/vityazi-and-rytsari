package com.ipovselite;

import java.util.Scanner;

/**
 * Created by User on 14.06.2016.
 */
public class ConsoleUtils {
    public static void print(String s) {
        System.out.println(s);
    }

    public static int input(int min, int max, String msg) {
        int result = min;
        System.out.println(msg);
        boolean ok = false;
        Scanner sc = new Scanner(System.in);
        String input = "";
        while (!ok) {
            if (sc.hasNextLine()) {
                input = sc.nextLine();
                try {
                    result = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Введено не число!");
                    continue;
                }
                if (result < min || result > max) {
                    System.out.println("Число должно быть в диапазоне от " + min + " до " + max);
                } else {
                    ok = true;
                }
            }
        }

        return result;
    }
}
