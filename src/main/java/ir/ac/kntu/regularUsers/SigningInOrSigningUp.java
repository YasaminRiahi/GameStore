package ir.ac.kntu.regularUsers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scanner.scanString;
import static ir.ac.kntu.helpers.Scanner.scanUsers;
import static ir.ac.kntu.helpers.TextTypings.*;

public class SigningInOrSigningUp {

    private DataBase dataBase;

    public SigningInOrSigningUp(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void signInOrUp() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SIGN UP / SIGN IN )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter a number!");
                System.out.println("1)Sign in");
                System.out.println("2)Sign up");
                String whatToDo = scanString();
                if (whatToDo.equals("1")) {
                    signIn();
                } else if (whatToDo.equals("2")) {
                    signUp();
                } else {
                    incorrect();
                    signInOrUp();
                }
            }
            case "2" -> {
                MainMenu mainMenu = new MainMenu(dataBase);
                mainMenu.showMenu();
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                signInOrUp();
            }
        }
    }

    public void signIn() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SIGN IN )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter your username:");
                String username = scanString();
                System.out.println("Enter your password:");
                String password = scanString();
                checkUsernameAndPassword(username, password);
            }
            case "2" -> signInOrUp();
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                signIn();
            }
        }
    }

    public void checkUsernameAndPassword(String username, String password) {
        for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
            if (dataBase.getRegularUsers().get(i).getUserName().equals(username) &&
                    dataBase.getRegularUsers().get(i).getPassword().equals(password)) {
                System.out.println("You're logged in successfully!!");
                Stopwatch1 stopwatch1 = new Stopwatch1();
                stopwatch1.start();
                RegularUserPage regularUserPage = new RegularUserPage(dataBase);
                regularUserPage.userAccess(i, stopwatch1);
            }
        }
        System.out.println(ConsoleColors.RED + "There isn't any account with this information!Try again"
                + ConsoleColors.RESET);
        signIn();
    }

    public void signUp() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SIGN UP )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> enterInformation();
            case "2" -> signInOrUp();
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                signUp();
            }
        }
    }

    public void enterInformation() {
        RegularUser user = scanUsers(dataBase);
        dataBase.addRegularUser(user);
        System.out.println("You signed up successfully!");
        DaoWriter.writeData(dataBase);
        Stopwatch1 stopwatch1 = new Stopwatch1();
        stopwatch1.start();
        RegularUserPage regularUserPage = new RegularUserPage(dataBase);
        regularUserPage.userAccess(dataBase.getRegularUsers().size() - 1, stopwatch1);
    }
}
