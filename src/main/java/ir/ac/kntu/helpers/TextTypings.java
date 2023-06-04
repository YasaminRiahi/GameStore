package ir.ac.kntu.helpers;

import static ir.ac.kntu.helpers.Scan.*;

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
        String input = scanString();
        return input;
    }

    public static void noAccount() {
        System.out.println(ConsoleColors.RED + "There isn't any account with this information!" + ConsoleColors.RESET);
    }

    public static String forWrongInputs() {
        System.out.println(ConsoleColors.RED_BOLD + "Wrong input!" + ConsoleColors.RESET);
        System.out.println("1)Retry");
        System.out.println("2)Turn back");
        String newInput = scanString();
        return newInput;
    }

    public static void incorrect() {
        System.out.println(ConsoleColors.RED + "Incorrect number!Enter again!" + ConsoleColors.RESET);
    }

    public static void toChangeWhat(String what) {
        System.out.println("Enter new " + what + ":");
    }

    public static String wantBuy() {
        System.out.println("You don't have this game , Do you want to buy it?");
        System.out.println("1)Yes");
        System.out.println("2)No");
        return scanString();
    }

    public static String communityOrRate() {
        System.out.println("Enter a number");
        System.out.println("1)See community of this game");
        System.out.println("2)Rate this game");
        System.out.println("3)Go to previous page");
        return scanString();
    }

    public static String submitIdea() {
        System.out.println("Do you want to submit your idea?");
        System.out.println("1)Yes");
        System.out.println("2)No");
        return scanString();
    }

    public static String acceptOrDecline() {
        System.out.println("Enter a number");
        System.out.println("1)Choose a person to accept or decline");
        System.out.println("2)Go to previous page");
        return scanString();
    }

    public static void exit() {
        System.exit(0);
    }
}
