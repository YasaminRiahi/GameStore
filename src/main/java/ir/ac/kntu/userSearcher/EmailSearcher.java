package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.ChangingUserInformation;
import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.admins.managers.ViewingUserInformation;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scanner.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class EmailSearcher {

    private DataBase dataBase;

    public EmailSearcher(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void searchByEmail(String goBack, int whichUser) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH BY EMAIL )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                searchEmails(goBack, whichUser);
                ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                managerUserPage.usersPage(whichUser);
            }
            case "2" -> {
                UserSearcher userSearcher = new UserSearcher(dataBase);
                userSearcher.howToSearch(goBack, whichUser);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                searchByEmail(goBack, whichUser);
            }
        }
    }

    public void searchEmails(String goBack, int whichUser) {
        System.out.println("Enter email:");
        String email = scanString();
        ArrayList<Integer> foundUsers = search(email);
        if (foundUsers.size() == 0) {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
            searchByEmail(goBack, whichUser);
        } else {
            showFoundUsers(foundUsers);
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= foundUsers.size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                searchEmails(goBack, whichUser);
            } else {
                if (goBack.equals("VIEW_USER_INFORMATION")) {
                    ViewingUserInformation viewUserInformation = new ViewingUserInformation(dataBase);
                    viewUserInformation.showUser(foundUsers.get(Integer.parseInt(whichOne) - 1));
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                } else if (goBack.equals("CHANGE_A_USER_INFORMATION")) {
                    ChangingUserInformation changeUserInformation = new ChangingUserInformation(dataBase);
                    changeUserInformation.toChange(foundUsers.get(Integer.parseInt(whichOne) - 1), whichUser);
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                } else {
                    for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
                        dataBase.getRegularUsers().get(i).getFriends().
                                remove(dataBase.getRegularUsers().get(foundUsers.get(Integer.parseInt(whichOne) - 1)));
                    }
                    dataBase.getRegularUsers().remove(dataBase.getRegularUsers().get(foundUsers.get(Integer.parseInt(whichOne) - 1)));
                    System.out.println("User removed successfully!");
                    DaoWriter.writeData(dataBase);
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                }
            }
        }
    }

    public ArrayList search(String toFind) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
            if (dataBase.getRegularUsers().get(i).getEmail().toLowerCase().startsWith(toFind.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void showFoundUsers(ArrayList<Integer> foundUsers) {
        int j = 1;
        for (Integer foundUser : foundUsers) {
            System.out.print(j + ")");
            System.out.println(dataBase.getRegularUsers().get(foundUser).getEmail());
            j++;
        }
    }
}