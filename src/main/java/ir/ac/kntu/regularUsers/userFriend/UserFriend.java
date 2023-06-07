package ir.ac.kntu.regularUsers.userFriend;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.regularUsers.RegularUser;
import ir.ac.kntu.regularUsers.RegularUserPage;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.Scanner.*;

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
        switch (nextChoose) {
            case "1" -> showUserFriendOptions(userIndex, stopwatch1);
            case "2" -> {
                RegularUserPage regularUserPage = new RegularUserPage(dataBase);
                regularUserPage.userAccess(userIndex, stopwatch1);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                friend(userIndex, stopwatch1);
            }
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
            searchInFriends(whichUser, stopwatch1);
        } else if (userFriendOptions == UserFriendOptions.FIND_FRIENDS) {
            findFriends(whichUser, stopwatch1);
        } else {
            requests(whichUser, stopwatch1);
        }
    }

    public void listOfFriends(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( LIST OF FRIENDS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                showFriends(userIndex, stopwatch1);
                String whichFriends = scanString();
                if (Integer.parseInt(whichFriends) - 1 >= dataBase.getRegularUsers().get(userIndex).getFriends().size()
                        || Integer.parseInt(whichFriends) - 1 < 0) {
                    incorrect();
                    listOfFriends(userIndex, stopwatch1);
                } else {
                    int friendIndex = Integer.parseInt(whichFriends) - 1;
                    showFriendProducts(userIndex, friendIndex);
                    friend(userIndex, stopwatch1);
                }
            }
            case "2" -> friend(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                listOfFriends(userIndex, stopwatch1);
            }
        }
    }

    public void searchInFriends(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH IN LIST OF FRIENDS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter friend username :");
                ArrayList<Integer> foundFriends = searchByUsername(scanString(), dataBase.getRegularUsers().get(userIndex).getFriends());
                if (foundFriends.size() == 0) {
                    System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
                    searchInFriends(userIndex, stopwatch1);
                } else {
                    showFoundUsers(foundFriends, dataBase.getRegularUsers().get(userIndex).getFriends());
                    String whichGame = scanString();
                    if (Integer.parseInt(whichGame) - 1 >= foundFriends.size() || Integer.parseInt(whichGame) - 1 < 0) {
                        incorrect();
                        searchInFriends(userIndex, stopwatch1);
                    } else {
                        int friendIndex = foundFriends.get(Integer.parseInt(whichGame) - 1);
                        showFriendProducts(userIndex, friendIndex);
                        friend(userIndex, stopwatch1);
                    }
                }
            }
            case "2" -> friend(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                searchInFriends(userIndex, stopwatch1);
            }
        }
    }

    public void findFriends(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( FINDING FRIENDS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                System.out.println("Enter friend username :");
                ArrayList<Integer> foundUsers = searchByUsername(scanString(), dataBase.getRegularUsers());
                if (foundUsers.size() == 0) {
                    System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
                    findFriends(userIndex, stopwatch1);
                } else {
                    for (int i = 0; i < foundUsers.size(); i++) {
                        if (foundUsers.get(i) == userIndex) {
                            foundUsers.remove(foundUsers.get(i));
                            break;
                        }
                    }
                    showFoundUsers(foundUsers, dataBase.getRegularUsers());
                    String whichUser = scanString();
                    if (Integer.parseInt(whichUser) - 1 >= foundUsers.size() || Integer.parseInt(whichUser) - 1 < 0) {
                        incorrect();
                        findFriends(userIndex, stopwatch1);
                    } else {
                        int foundUserIndex = foundUsers.get(Integer.parseInt(whichUser) - 1);
                        sendRequestPage(userIndex, foundUserIndex, stopwatch1);
                    }
                }
            }
            case "2" -> friend(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                findFriends(userIndex, stopwatch1);
            }
        }
    }

    public void requests(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REQUEST PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                if (dataBase.getRegularUsers().get(userIndex).getRequests().size() != 0) {
                    showRequests(userIndex, stopwatch1);
                } else {
                    System.out.println("You don't have any request!");
                    friend(userIndex, stopwatch1);
                }
            }
            case "2" -> friend(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                requests(userIndex, stopwatch1);
            }
        }
    }

    public void showRequests(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SHOW REQUESTS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                int j = 1;
                for (int i = 0; i < dataBase.getRegularUsers().get(userIndex).getRequests().size(); i++) {
                    System.out.println(j + ")" + dataBase.getRegularUsers().get(userIndex).getRequests().get(i).getUserName());
                }
                answerRequest(userIndex, stopwatch1);
            }
            case "2" -> requests(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                showRequests(userIndex, stopwatch1);
            }
        }
    }

    public void answerRequest(int userIndex, Stopwatch1 stopwatch1) {
        System.out.println("Choose one of the users");
        String whichUser = scanString();
        if (Integer.parseInt(whichUser) - 1 >= dataBase.getRegularUsers().get(userIndex).getRequests().size() ||
                Integer.parseInt(whichUser) - 1 < 0) {
            incorrect();
            answerRequest(userIndex, stopwatch1);
        } else {
            int index = Integer.parseInt(whichUser) - 1;
            System.out.println("1)Accept this user");
            System.out.println("2)Decline this user");
            System.out.println("3)Go to previous page");
            switch (scanString()) {
                case "1" -> {
                    RegularUser friend;
                    friend = dataBase.getRegularUsers().get(userIndex).getRequests().get(index);
                    dataBase.getRegularUsers().get(userIndex).getFriends().add(friend);
                    System.out.println("User added to your friends successfully!");
                    dataBase.getRegularUsers().get(userIndex).getRequests().remove(index);
                    friend.getFriends().add(dataBase.getRegularUsers().get(userIndex));
                    friend.getRequests().remove(dataBase.getRegularUsers().get(userIndex));
                    requests(userIndex, stopwatch1);
                }
                case "2" -> {
                    dataBase.getRegularUsers().get(userIndex).getRequests().remove(index);
                    System.out.println("User declined successfully!");
                    requests(userIndex, stopwatch1);
                }
                case "3" -> showRequests(userIndex, stopwatch1);
                default -> {
                    incorrect();
                    answerRequest(userIndex, stopwatch1);
                }
            }
        }
    }

    public ArrayList searchByUsername(String name, ArrayList<RegularUser> list) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().toLowerCase().startsWith(name.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void showFoundUsers(ArrayList<Integer> foundUsers, ArrayList<RegularUser> list) {
        System.out.println("Choose one of these users!");
        int j = 1;
        for (Integer foundUser : foundUsers) {
            System.out.println(j + ")" + list.get(foundUser).getUserName());
            j++;
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
                System.out.println(j + ")" + dataBase.getRegularUsers().get(userIndex).getFriends().get(i).getUserName());
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
        for (MonitorGaming monitorGaming : friend.getMonitorGaming().keySet()) {
            String key = monitorGaming.getName();
            int value = friend.getMonitorGaming().get(monitorGaming);
            System.out.println("    " + j + ")" + key + " (" + value + ")");
            j++;
        }
        System.out.println("Your friend gaming pad(s):");
        j = 1;
        for (GamePad gamePad : friend.getGamePad().keySet()) {
            String key = gamePad.getName();
            int value = friend.getGamePad().get(gamePad);
            System.out.println("    " + j + ")" + key + " (" + value + ")");
            j++;
        }
    }

    public void sendRequestPage(int userIndex, int foundUserIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEND REQUEST )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                if (isFriendOrNo(userIndex, foundUserIndex) == 0) {
                    System.out.println("Do you want to request this user?");
                    System.out.println("1)Yes");
                    System.out.println("2)No");
                    switch (scanString()) {
                        case "1" -> sendRequest(userIndex, foundUserIndex, stopwatch1);
                        case "2" -> findFriends(userIndex, stopwatch1);
                        default -> {
                            incorrect();
                            sendRequestPage(userIndex, foundUserIndex, stopwatch1);
                        }
                    }
                } else {
                    System.out.println("You've already had this user as your friend!");
                    findFriends(userIndex, stopwatch1);
                }
            }
            case "2" -> findFriends(userIndex, stopwatch1);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                sendRequestPage(userIndex, foundUserIndex, stopwatch1);
            }
        }
    }

    public int isFriendOrNo(int userIndex, int foundUserIndex) {
        RegularUser user;
        user = dataBase.getRegularUsers().get(userIndex);
        RegularUser foundUser;
        foundUser = dataBase.getRegularUsers().get(foundUserIndex);
        for (int i = 0; i < user.getFriends().size(); i++) {
            if (user.getFriends().get(i) == foundUser) {
                return 1;
            }
        }
        return 0;
    }

    public void sendRequest(int userIndex, int foundUser, Stopwatch1 stopwatch1) {
        if (isRequested(userIndex, foundUser) == 0) {
            dataBase.getRegularUsers().get(foundUser).getRequests().add(dataBase.getRegularUsers().get(userIndex));
            System.out.println("You requested successfully");
        } else {
            System.out.println("You've already requested this user and she/he has not accepted or declined!");
        }
        findFriends(userIndex, stopwatch1);
    }

    public int isRequested(int userIndex, int foundUserIndex) {
        if (dataBase.getRegularUsers().get(foundUserIndex).getRequests().contains(dataBase.getRegularUsers().get(userIndex))) {
            return 1;
        }
        return 0;
    }
}