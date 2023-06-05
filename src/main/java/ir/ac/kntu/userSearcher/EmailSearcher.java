package ir.ac.kntu.userSearcher;

import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.Scan.scanString;
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
        if (nextChoose.equals("1")) {
            searchEmails(goBack,whichUser);
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
            searchByEmail(goBack,whichUser);
        }
    }

    public void searchEmails(String goBack, int whichUser) {
        System.out.println("Enter email:");
        String email= scanString();
        ArrayList<Integer> foundUsers = search(email);
        if (foundUsers.size() == 0) {
            System.out.println(ConsoleColors.RED + "No result!Try again" + ConsoleColors.RESET);
            searchByEmail(goBack,whichUser);
        } else {
            showFoundUsers(foundUsers);
            String whichOne = scanString();
            if (Integer.parseInt(whichOne) - 1 >= foundUsers.size() || Integer.parseInt(whichOne) - 1 < 0) {
                incorrect();
                searchEmails(goBack,whichUser);
            } else {
                if (goBack.equals("VIEW_USER_INFORMATION")) {
                    showUser(foundUsers.get(Integer.parseInt(whichOne) - 1));
                    ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                    managerUserPage.usersPage(whichUser);
                }
//                else if (goBack.equals("")) {
//                    toChange(foundUsers.get(Integer.parseInt(whichUser) - 1), store);
//                    usersPage(store);
//                } else {
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

    public void showUser(int index) {
        System.out.println("1)Username : " + dataBase.getRegularUsers().get(index).getUserName());
        System.out.println("2)Password : " +dataBase.getRegularUsers().get(index).getPassword());
        System.out.println("3)Phone number : " + dataBase.getRegularUsers().get(index).getPhoneNumber());
        System.out.println("4)Email : " + dataBase.getRegularUsers().get(index).getEmail());
        System.out.println("5)Wallet : " + dataBase.getRegularUsers().get(index).getWallet());
        System.out.println("6)Score : " + dataBase.getRegularUsers().get(index).getScore());
        System.out.println("7)Games :");
        int j = 1;
        for (int i = 0; i < dataBase.getRegularUsers().get(index).getMyGames().size(); i++) {
            System.out.println("    " + j + ")" + dataBase.getRegularUsers().get(index).getMyGames().get(i).getName());
            j++;
        }
        System.out.println("8)Monitor Gaming :");
        j = 1;
        for (MonitorGaming monitorGaming: dataBase.getRegularUsers().get(index).getMonitorGaming().keySet()) {
            String key = monitorGaming.getName();
            int value = dataBase.getRegularUsers().get(index).getMonitorGaming().get(monitorGaming);
            System.out.println("    " + j + ")" + key + " (" + value +")");
            j++;
        }
        System.out.println("8)Game pad :");
        j = 1;
        for (GamePad gamePad: dataBase.getRegularUsers().get(index).getGamePad().keySet()) {
            String key = gamePad.getName();
            int value = dataBase.getRegularUsers().get(index).getGamePad().get(gamePad);
            System.out.println("    " + j + ")" + key + " (" + value +")");
            j++;
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
        for (int i = 0; i < foundUsers.size(); i++) {
            System.out.print(+j + ")");
            System.out.println(dataBase.getRegularUsers().get(foundUsers.get(i)).getEmail());
            j++;
        }
    }
}
