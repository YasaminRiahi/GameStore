package ir.ac.kntu.regularUsers.profile;

import ir.ac.kntu.admins.managers.ChangeUserInformation;
import ir.ac.kntu.admins.managers.ViewUserInformation;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.regularUsers.RegularUserPage;
import ir.ac.kntu.regularUsers.Stopwatch1;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.TextTypings.*;

public class UserProfile {

    private DataBase dataBase;

    public UserProfile(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void profile(int userIndex, Stopwatch1 stopwatch1) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( PROFILE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            ViewUserInformation viewUserInformation = new ViewUserInformation(dataBase);
            viewUserInformation.showUser(userIndex);
            System.out.println("Do you want to change your information?");
            System.out.println("1)Yes");
            System.out.println("2)No");
            String changeOrNo = scanString();
            if (changeOrNo.equals("1")) {
                toChange(userIndex, stopwatch1);
            } else if (changeOrNo.equals("2")) {
                profile(userIndex, stopwatch1);
            } else {
                incorrect();
                profile(userIndex, stopwatch1);
            }
        } else if (nextChoose.equals("2")) {
            RegularUserPage regularUserPage = new RegularUserPage(dataBase);
            regularUserPage.userAccess(userIndex, stopwatch1);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            profile(userIndex, stopwatch1);
        }
    }

    public void toChange(int userIndex, Stopwatch1 stopwatch1) {
        whichOption();
        String which = scanString();
        switch (Integer.parseInt(which)) {
            case 1:
                toChangeWhat("username");
                dataBase.getRegularUsers().get(userIndex).setUserName(scanNewName(dataBase, scanString()));
                break;
            case 2:
                toChangeWhat("password");
                dataBase.getRegularUsers().get(userIndex).setPassword(scanNewPass(dataBase, scanString()));
                break;
            case 3:
                toChangeWhat("phone number");
                dataBase.getRegularUsers().get(userIndex).setPhoneNumber(scanString());
                break;
            case 4:
                toChangeWhat("email");
                dataBase.getRegularUsers().get(userIndex).setEmail(scanString());
                break;
            case 5:
                chargeWallet(userIndex);
                break;
            default:
                incorrect();
                toChange(userIndex, stopwatch1);
        }
        System.out.println("The change was successful!");
        profile(userIndex, stopwatch1);
    }

    public void whichOption() {
        System.out.println("Which one do you want to change?");
        System.out.println("1)Username");
        System.out.println("2)Password");
        System.out.println("3)Phone number");
        System.out.println("4)Email");
        System.out.println("5)Wallet");
    }

    public void chargeWallet(int userIndex) {
        double currentBalance = dataBase.getRegularUsers().get(userIndex).getWallet();
        System.out.println("Current wallet balance : " + currentBalance);
        System.out.println("How much do you want to charge your wallet?");
        dataBase.getRegularUsers().get(userIndex).setWallet(currentBalance + scanDouble());
    }
}