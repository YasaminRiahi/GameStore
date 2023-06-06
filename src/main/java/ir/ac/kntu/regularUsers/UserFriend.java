package ir.ac.kntu.regularUsers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;

public class UserFriend {

    private DataBase dataBase;

    private UserFriendOptions userFriendOptions;

    public UserFriend(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void friend(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FRIEND )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showUserFriendOptions(userIndex, stopwatch1);
        } else if (nextChoose.equals("2")) {
            RegularUserPage regularUserPage = new RegularUserPage(dataBase);
            regularUserPage.userAccess(userIndex, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            friend(userIndex, stopwatch1);
        }
    }


    public void showUserFriendOptions(int whichUser, Stopwatch1 stopwatch1) {
        for (UserFriendOptions userFriendOptions1 : UserFriendOptions.values()) {
            System.out.print(userFriendOptions1.getValue() + ")");
            System.out.println(userFriendOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, stopwatch1);
    }

    public void fromValue(String value, int whichUser, Stopwatch1 stopwatch1) {
        for (UserFriendOptions e : UserFriendOptions.values()) {
            if (e.getValue().equals(value)) {
                this.userFriendOptions = e;
                goToOptions(whichUser, stopwatch1);
            }
        }
        incorrect();
        showUserFriendOptions(whichUser, stopwatch1);
    }

    public void goToOptions(int whichUser, Stopwatch1 stopwatch1) {
        if (userFriendOptions == UserFriendOptions.LIST_OF_FRIENDS) {
            ;
        } else if (userFriendOptions == UserFriendOptions.SEARCH_FRIENDS) {
            ;
        } else if (userFriendOptions == UserFriendOptions.FIND_FRIENDS) {
            ;
        } else {
            ;
        }
    }
}
