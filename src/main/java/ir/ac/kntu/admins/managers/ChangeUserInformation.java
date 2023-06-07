package ir.ac.kntu.admins.managers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;
import ir.ac.kntu.userSearcher.UserSearcher;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ChangeUserInformation {

    private DataBase dataBase;

    public ChangeUserInformation(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void changeAUserInformation(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE USERS INFORMATION )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                UserSearcher userSearcher = new UserSearcher(dataBase);
                userSearcher.howToSearch("CHANGE_A_USER_INFORMATION", whichManager);
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
                changeAUserInformation(whichManager);
            }
        }
    }

    public void toChange(int userIndex, int whichManager) {
        whichOption();
        String which = scanString();
        switch (Integer.parseInt(which)) {
            case 1 -> {
                toChangeWhat("username");
                dataBase.getRegularUsers().get(userIndex).setUserName(scanNewName(dataBase, scanString()));
            }
            case 2 -> {
                toChangeWhat("password");
                dataBase.getRegularUsers().get(userIndex).setPassword(scanNewPass(scanString()));
            }
            case 3 -> {
                toChangeWhat("phone number");
                dataBase.getRegularUsers().get(userIndex).setPhoneNumber(scanString());
            }
            case 4 -> {
                toChangeWhat("email");
                dataBase.getRegularUsers().get(userIndex).setEmail(scanString());
            }
            case 5 -> {
                toChangeWhat("wallet balance");
                dataBase.getRegularUsers().get(userIndex).setWallet(scanDouble());
            }
            case 6 -> {
                toChangeWhat("Score");
                dataBase.getRegularUsers().get(userIndex).setScore(scanInt());
            }
            default -> {
                incorrect();
                toChange(userIndex, whichManager);
            }
        }
        System.out.println("User changed successfully!");
        changeAUserInformation(whichManager);
    }

    public void whichOption() {
        System.out.println("Which one do you want to change?");
        System.out.println("1)Username");
        System.out.println("2)Password");
        System.out.println("3)Phone number");
        System.out.println("4)Email");
        System.out.println("5)Wallet");
        System.out.println("6)Score");
    }
}
