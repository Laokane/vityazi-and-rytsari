package com.ipovselite;

import javax.swing.*;

/**
 * Created by User on 18.06.2016.
 */
public class UserInterface {
    private static JTextArea output;
    public static void setOutput(JTextArea ta) { output = ta; }
    public static void print(String s) {
        String msg = "\n" + s;
        output.append(msg);
    }

    public static int input(int min, int max, JTextField inputField, JTextArea msgArea, String fieldName) throws InputValidatorException {
        String input = inputField.getText();
        int number = 0;
        if (input.trim().length() == 0) {
            msgArea.append("\n" + "Поле " + fieldName + " не должно быть пустым!");
            throw new InputValidatorException();
        }
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            msgArea.append("\nВ поле " + fieldName + " введено не число!");
            throw new InputValidatorException();
        }
        if (number < min || number > max) {
            msgArea.append("\nВ поле " + fieldName + " число должно быть в пределах от " + min + " до " + max);
            throw new InputValidatorException();
        }

        return number;
    }
}
