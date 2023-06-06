package ir.ac.kntu.regularUsers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.Scan.scanUsers;
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
        if (nextChoose.equals("1")) {
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
        } else if (nextChoose.equals("2")) {
            MainMenu mainMenu = new MainMenu(dataBase);
            mainMenu.showMenu();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            signInOrUp();
        }
    }

    public void signIn() {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SIGN IN )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            System.out.println("Enter your username:");
            String username = scanString();
            System.out.println("Enter your password:");
            String password = scanString();
            checkUsernameAndPassword(username, password);
        } else if (nextChoose.equals("2")) {
            signInOrUp();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            signIn();
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
        if (nextChoose.equals("1")) {
            enterInformation();
        } else if (nextChoose.equals("2")) {
            signInOrUp();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            signUp();
        }
    }

    public void enterInformation() {
        RegularUser user = scanUsers(dataBase);
        dataBase.addRegularUser(user);
        System.out.println("You signed up successfully!");
        Stopwatch1 stopwatch1 = new Stopwatch1();
        stopwatch1.start();
        RegularUserPage regularUserPage = new RegularUserPage(dataBase);
        regularUserPage.userAccess(dataBase.getRegularUsers().size() - 1, stopwatch1);
    }
}
