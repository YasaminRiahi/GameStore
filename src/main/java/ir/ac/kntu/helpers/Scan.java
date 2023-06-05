package ir.ac.kntu.helpers;

import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.regularUsers.RegularUser;
import ir.ac.kntu.store.DataBase;

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

    public static RegularUser scanUsers(DataBase dataBase) {
        RegularUser newUser = new RegularUser();
        System.out.println("Enter username :");
        String username = scanString();
        if (duplicateUsername(dataBase, username) == 0) {
            newUser.setUserName(username);
            System.out.println("Enter user password :");
            String password = scanString();
            while (checkPassword(password) == 1) {
                System.out.println("Enter user password :");
                password = scanString();
            }
            newUser.setPassword(password);
            System.out.println("Enter user phone number :");
            newUser.setPhoneNumber(scanString());
            System.out.println("Enter user email :");
            newUser.setEmail(scanString());
            newUser.setWallet(0);
            newUser.setScore(0);
            return newUser;
        } else {
            System.out.println(ConsoleColors.RED + "Duplicate username!Enter another one!" + ConsoleColors.RESET);
            scanUsers(dataBase);
        }
        return newUser;
    }

    public static int duplicateUsername(DataBase dataBase, String username) {
        for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
            if (dataBase.getRegularUsers().get(i).getUserName().equals(username)) {
                return 1;
            }
        }
        return 0;
    }

    public static int checkPassword(String password) {
        if (!password.matches(".*" + "[A-Z]+" + ".*") ||
                !password.matches(".*" + "[a-z]+" + ".*") ||
                !password.matches(".*" + "[0-9]+" + ".*")) {
            System.out.println(ConsoleColors.RED + "Password must include at least 1 numbers , 1 captal and \n" +
                    "1 small letter!" + ConsoleColors.RESET);
            return 1;
        } else {
            if (password.length() < 8) {
                System.out.println(ConsoleColors.RED + "Password must include at least 8 letters!" + ConsoleColors.RESET);
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static String scanNewName(DataBase dataBase, String newUsername) {
        while (duplicateUsername(dataBase, newUsername) == 1) {
            System.out.println(ConsoleColors.RED + "Duplicate username!Enter another one!" + ConsoleColors.RESET);
            System.out.println("Enter another username:");
            newUsername = scanString();
        }
        return newUsername;
    }

    public static String scanNewPass(DataBase dataBase, String newPassword) {
        while (checkPassword(newPassword) == 1) {
            System.out.println("Enter another password:");
            newPassword = scanString();
        }
        return newPassword;
    }
}
