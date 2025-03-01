package ir.ac.kntu.helpers;

import ir.ac.kntu.admins.Admin;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scanner.*;

public class TextTypings {

    public static void drawingLines() {
        System.out.println(ConsoleColors.PURPLE_BOLD + "___________________" + ConsoleColors.RESET);
    }

    public static String getNumberFromOptions() {
        System.out.println("Enter a number!");
        return scanString();
    }

    public static String whereToGo() {
        System.out.println("1)Be in this page");
        System.out.println("2)Go to previous page");
        System.out.println("3)Exit");
        return scanString();
    }

    public static void noAccount() {
        System.out.println(ConsoleColors.RED + "There isn't any account with this information!" + ConsoleColors.RESET);
    }

    public static void showAdmins(int index, ArrayList<Admin> admins) {
        System.out.println("1)Username : " + admins.get(index).getUserName());
        System.out.println("2)Password : " + admins.get(index).getPassword());
        System.out.println("3)Phone number : " + admins.get(index).getPhoneNumber());
        System.out.println("4)Email : " + admins.get(index).getEmail());
    }

    public static void changedSuccessfully() {
        System.out.println(ConsoleColors.GREEN_BOLD + "Your information changed successfully!"
                + ConsoleColors.RESET);
    }

    public static void incorrect() {
        System.out.println(ConsoleColors.RED + "Incorrect number!Enter again!" + ConsoleColors.RESET);
    }

    public static void toChangeWhat(String what) {
        System.out.println("Enter new " + what + ":");
    }

    public static String wantBuy() {
        System.out.println("Do you want to buy this product?");
        System.out.println("1)Yes");
        System.out.println("2)No");
        return scanString();
    }

    public static void notDeveloper() {
        System.out.println(ConsoleColors.RED + "You're not the developer of this game!" + ConsoleColors.RESET);
    }

    public static void notSeller() {
        System.out.println(ConsoleColors.RED + "You're not the seller of this accessory!" + ConsoleColors.RESET);
    }

    public static void exit() {
        System.exit(0);
    }

    public static void drawForGames(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(ConsoleColors.BLUE + "*" + ConsoleColors.RESET);
        }
        System.out.println();
    }

    public static void drawForAccessories(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(ConsoleColors.BLUE + "-" + ConsoleColors.RESET);
        }
        System.out.println();
    }

    public static String continueOrNo() {
        System.out.println("Do you want to choose anything else?");
        System.out.println("1)Yes");
        System.out.println("2)No");
        return scanString();
    }

    public static String communityOrRate() {
        System.out.println("Enter a number");
        System.out.println("1)Add community");
        System.out.println("2)Rate this game");
        System.out.println("3)Go to previous page");
        return scanString();
    }

    public static String communityOrReport() {
        System.out.println("Enter a number");
        System.out.println("1)Add community");
        System.out.println("2)Report a crash");
        System.out.println("3)Go to previous page");
        return scanString();
    }

    public static String feedbackOrRate() {
        System.out.println("Enter a number");
        System.out.println("1)Add feedback");
        System.out.println("2)Rate this game");
        System.out.println("3)Go to previous page");
        return scanString();
    }
}