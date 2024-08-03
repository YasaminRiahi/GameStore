package ir.ac.kntu.regularUsers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.profile.UserProfile;
import ir.ac.kntu.regularUsers.userFriend.UserFriend;
import ir.ac.kntu.regularUsers.userLibrary.UserLibrary;
import ir.ac.kntu.regularUsers.userStore.UserStore;
import ir.ac.kntu.store.DaoWriter;
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
        switch (nextChoose) {
            case "1" -> showUserOptions(userIndex, stopwatch1);
            case "2" -> {
                stopwatch1.stop();
                System.out.println("You signed out!");
                int time = (int) stopwatch1.getElapsedSeconds() / 10;
                int newScore = dataBase.getRegularUsers().get(userIndex).getScore() + time;
                dataBase.getRegularUsers().get(userIndex).setScore(newScore);
                DaoWriter.writeData(dataBase);
                SigningInOrSigningUp signingInOrSigningUp = new SigningInOrSigningUp(dataBase);
                signingInOrSigningUp.signInOrUp();
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                userAccess(userIndex, stopwatch1);
            }
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
            UserStore userStore = new UserStore(dataBase);
            userStore.userStore(whichUser,stopwatch1);
        } else if (userOptions == UserOptions.LIBRARY) {
            UserLibrary userLibrary = new UserLibrary(dataBase);
            userLibrary.userLibrary(whichUser,stopwatch1);
        } else {
            UserFriend userFriend = new UserFriend(dataBase);
            userFriend.friend(whichUser,stopwatch1);
        }
    }
}