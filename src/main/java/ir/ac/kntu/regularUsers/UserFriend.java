package ir.ac.kntu.regularUsers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.Scan.*;

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
            listOfFriends(whichUser, stopwatch1);
        } else if (userFriendOptions == UserFriendOptions.SEARCH_FRIENDS) {
            ;
        } else if (userFriendOptions == UserFriendOptions.FIND_FRIENDS) {
            ;
        } else {
            ;
        }
    }

    public void listOfFriends(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIST OF FRIENDS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showFriends(userIndex, stopwatch1);
            String whichFriends = scanString();
            if (Integer.parseInt(whichFriends) - 1 >= dataBase.getRegularUsers().get(userIndex).getFriends().size()
                    || Integer.parseInt(whichFriends) - 1 < 0) {
                incorrect();
                listOfFriends(userIndex, stopwatch1);
            } else {
                int friendIndex = Integer.parseInt(whichFriends) - 1;
                showFriendProducts(userIndex,friendIndex);
                friend(userIndex,stopwatch1);
            }
        } else if (nextChoose.equals("2")) {
            friend(userIndex, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            listOfFriends(userIndex, stopwatch1);
        }
    }

    public void showFriends(int userIndex, Stopwatch1 stopwatch1) {
        if (dataBase.getRegularUsers().get(userIndex).getFriends().size() == 0) {
            System.out.println("You don't have any friends");
            friend(userIndex, stopwatch1);
        } else {
            System.out.println("Choose one of your friends");
            int j = 1;
            for (int i = 0; i < dataBase.getRegularUsers().get(userIndex).getFriends().size(); i++) {
                System.out.println(+j + ")" + dataBase.getRegularUsers().get(userIndex).getFriends().get(i).getUserName());
                j++;
            }
        }
    }

    public void showFriendProducts(int userIndex, int friendIndex) {
        RegularUser friend = dataBase.getRegularUsers().get(userIndex).getFriends().get(friendIndex);
        System.out.println("Your friend game(s):");
        int j = 1;
        for (int i = 0; i < friend.getMyGames().size(); i++) {
            System.out.println("    " + j + ")" + friend.getMyGames().get(i).getName());
            j++;
        }
        System.out.println("Your friend gaming monitor(s):");
        j = 1;
        for (MonitorGaming monitorGaming: friend.getMonitorGaming().keySet()) {
            String key = monitorGaming.getName();
            int value = friend.getMonitorGaming().get(monitorGaming);
            System.out.println("    " + j + ")" + key + " (" + value +")");
            j++;
        }
        System.out.println("Your friend gaming pad(s):");
        j = 1;
        for (GamePad gamePad: friend.getGamePad().keySet()) {
            String key = gamePad.getName();
            int value = friend.getGamePad().get(gamePad);
            System.out.println("    " + j + ")" + key + " (" + value +")");
            j++;
        }
    }
}
