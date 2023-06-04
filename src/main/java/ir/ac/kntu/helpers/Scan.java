package ir.ac.kntu.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Scan {

    public static String scanString() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public static Integer scanInt() {
        Scanner scanner = new Scanner(System.in);
        int input;
        String isInput = scanString();
        if (isInput.matches("[0-9]+")) {
            input = Integer.parseInt(isInput);
            return input;
        }
        return null;
    }

    public static Double scanDouble() {
        Scanner scanner = new Scanner(System.in);
        double input;
        String isInput = scanString();
        if (isInput.matches("[0-9]+(.[0-9.])*")) {
            input = Double.parseDouble(isInput);
            return input;
        }
        return null;
    }

}
