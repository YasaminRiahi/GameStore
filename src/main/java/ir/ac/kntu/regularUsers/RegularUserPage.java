package ir.ac.kntu.regularUsers;

import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.admins.developers.AddDevelopersToGame;
import ir.ac.kntu.admins.developers.DeveloperGamePage;
import ir.ac.kntu.admins.developers.DeveloperOptions;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;

public class RegularUserPage {

    private DataBase dataBase;

    private UserOptions userOptions;

    public RegularUserPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void userAccess(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( USER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showUserOptions(userIndex, stopwatch1);
        } else if (nextChoose.equals("2")) {
            stopwatch1.stop();
            System.out.println("You signed out!");
            int time = (int) stopwatch1.getElapsedSeconds() / 10;
            int newScore = dataBase.getRegularUsers().get(userIndex).getScore() + time;
            dataBase.getRegularUsers().get(userIndex).setScore(newScore);
            SigningInOrSigningUp signingInOrSigningUp = new SigningInOrSigningUp(dataBase);
            signingInOrSigningUp.signInOrUp();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            userAccess(userIndex, stopwatch1);
        }
    }

    public void showUserOptions(int whichUser, Stopwatch1 stopwatch1) {
        for (UserOptions userOptions1 : UserOptions.values()) {
            System.out.print(userOptions1.getValue() + ")");
            System.out.println(userOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, stopwatch1);
    }

    public void fromValue(String value, int whichUser, Stopwatch1 stopwatch1) {
        for (UserOptions e : UserOptions.values()) {
            if (e.getValue().equals(value)) {
                this.userOptions = e;
                goToOptions(whichUser, stopwatch1);
            }
        }
        incorrect();
        showUserOptions(whichUser, stopwatch1);
    }

    public void goToOptions(int whichUser, Stopwatch1 stopwatch1) {
        if (userOptions == UserOptions.PROFILE) {
            UserProfile userProfile = new UserProfile(dataBase);
            userProfile.profile(whichUser,stopwatch1);
        } else if (userOptions == UserOptions.STORE) {
            ;
        } else if (userOptions == UserOptions.LIBRARY) {
            ;
        } else {
            ;
        }
    }
}
