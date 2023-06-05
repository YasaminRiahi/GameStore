package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.admins.managers.ViewUserInformation;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class PhoneNumberSearcher {

    private DataBase dataBase;

    public PhoneNumberSearcher(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void searchByPhoneNumber(String goBack, int whichUser) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH BY PHONE NUMBER )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            searchPhoneNumbers(goBack, whichUser);
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichUser);
        } else if (nextChoose.equals("2")) {
            UserSearcher userSearcher = new UserSearcher(dataBase);
            userSearcher.howToSearch(goBack, whichUser);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            searchByPhoneNumber(goBack, whichUser);
        }
    }

    public void searchPhoneNumbers(String goBack, int whichUser) {
        System.out.println("Enter phone number:");
        String phoneNumber = scanString();
        ArrayList<Integer> foundUsers = search(phoneNumber);
        if (foundUsers.size() == 0) {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
            searchByPhoneNumber(goBack, whichUser);
        } else {
            showFoundUsers(foundUsers);
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= foundUsers.size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                searchPhoneNumbers(goBack, whichUser);
            } else {
                if (goBack.equals("VIEW_USER_INFORMATION")) {
                    ViewUserInformation viewUserInformation = new ViewUserInformation(dataBase);
                    viewUserInformation.showUser(foundUsers.get(Integer.parseInt(whichOne) - 1));
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                }
//                else if (goBack.equals("")) {
////                    toChange(foundUsers.get(Integer.parseInt(whichUser) - 1), store);
////                    usersPage(store);
//                } else {
////                    Users user = new Users();
////                    user = store.getUsers().get(foundUsers.get(Integer.parseInt(whichUser) - 1));
////                    for (int i = 0; i < store.getUsers().size(); i++) {
////                        store.getUsers().get(i).getFriends().remove(user);
////                    }
////                    store.getUsers().remove(foundUsers.get(Integer.parseInt(whichUser) - 1));
////                    System.out.println("User removed successfully!");
////                    usersPage(store);
//                }
            }
        }
    }


    public ArrayList search(String toFind) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
            if (dataBase.getRegularUsers().get(i).getPhoneNumber().toLowerCase().startsWith(toFind.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void showFoundUsers(ArrayList<Integer> foundUsers) {
        int j = 1;
        for (int i = 0; i < foundUsers.size(); i++) {
            System.out.print(+j + ")");
            System.out.println(dataBase.getRegularUsers().get(foundUsers.get(i)).getPhoneNumber());
            j++;
        }
    }
}
