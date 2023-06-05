package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.AddAUser;
import ir.ac.kntu.admins.managers.ChangeUserInformation;
import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.admins.managers.ViewUserInformation;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.helpers.ConsoleColors;
import java.util.ArrayList;
import static ir.ac.kntu.helpers.Scan.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;

public class UsernameSearcher {

    private DataBase dataBase;

    public UsernameSearcher(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void searchByUsername(String goBack, int whichUser) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SEARCH BY USERNAME )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            searchUsernames(goBack,whichUser);
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
            searchByUsername(goBack,whichUser);
        }
    }

    public void searchUsernames(String goBack, int whichUser) {
        System.out.println("Enter username:");
        String username = scanString();
        ArrayList<Integer> foundUsers = search(username);
        if (foundUsers.size() == 0) {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
            searchByUsername(goBack,whichUser);
        } else {
            showFoundUsers(foundUsers);
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= foundUsers.size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                searchByUsername(goBack,whichUser);
            } else {
                if (goBack.equals("VIEW_USER_INFORMATION")) {
                    ViewUserInformation viewUserInformation = new ViewUserInformation(dataBase);
                    viewUserInformation.showUser(foundUsers.get(Integer.parseInt(whichOne) - 1));
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                } else if (goBack.equals("CHANGE_A_USER_INFORMATION")) {
                    ChangeUserInformation changeUserInformation = new ChangeUserInformation(dataBase);
                    changeUserInformation.toChange(foundUsers.get(Integer.parseInt(whichOne) - 1),whichUser);
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                }
//                else {
//                    Users user = new Users();
//                    user = store.getUsers().get(foundUsers.get(Integer.parseInt(whichUser) - 1));
//                    for (int i = 0; i < store.getUsers().size(); i++) {
//                        store.getUsers().get(i).getFriends().remove(user);
//                    }
//                    store.getUsers().remove(foundUsers.get(Integer.parseInt(whichUser) - 1));
//                    System.out.println("User removed successfully!");
//                    usersPage(store);
//                }
            }
        }
    }


    public ArrayList search(String toFind) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();
        for (int i = 0; i < dataBase.getRegularUsers().size(); i++) {
            if (dataBase.getRegularUsers().get(i).getUserName().toLowerCase().startsWith(toFind.toLowerCase())) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public void showFoundUsers(ArrayList<Integer> foundUsers) {
        int j = 1;
        for (int i = 0; i < foundUsers.size(); i++) {
            System.out.print(+j + ")");
            System.out.println(dataBase.getRegularUsers().get(foundUsers.get(i)).getUserName());
            j++;
        }
    }
}