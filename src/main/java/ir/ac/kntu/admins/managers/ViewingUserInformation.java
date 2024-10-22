package ir.ac.kntu.admins.managers;

import ir.ac.kntu.products.accessories.gamePad.GamePad;
import ir.ac.kntu.products.accessories.monitorGaming.MonitorGaming;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.userSearcher.UserSearcher;

import static ir.ac.kntu.helpers.TextTypings.*;

public class ViewingUserInformation {

    private DataBase dataBase;

    public ViewingUserInformation(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void viewUsersInformation(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( VIEW USERS INFORMATION )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                UserSearcher userSearcher = new UserSearcher(dataBase);
                userSearcher.howToSearch("VIEW_USER_INFORMATION", whichManager);
            }
            case "2" -> {
                ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
                managerUserPage.usersPage(whichManager);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                viewUsersInformation(whichManager);
            }
        }
    }

    public void showUser(int index) {
        System.out.println("1)Username : " + dataBase.getRegularUsers().get(index).getUserName());
        System.out.println("2)Password : " + dataBase.getRegularUsers().get(index).getPassword());
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
        for (MonitorGaming monitorGaming : dataBase.getRegularUsers().get(index).getMonitorGaming().keySet()) {
            String key = monitorGaming.getName();
            int value = dataBase.getRegularUsers().get(index).getMonitorGaming().get(monitorGaming);
            System.out.println("    " + j + ")" + key + " (" + value + ")");
            j++;
        }
        System.out.println("8)Game pad :");
        j = 1;
        for (GamePad gamePad : dataBase.getRegularUsers().get(index).getGamePad().keySet()) {
            String key = gamePad.getName();
            int value = dataBase.getRegularUsers().get(index).getGamePad().get(gamePad);
            System.out.println("    " + j + ")" + key + " (" + value + ")");
            j++;
        }
    }
}