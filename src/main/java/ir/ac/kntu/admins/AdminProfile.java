package ir.ac.kntu.admins;

import ir.ac.kntu.admins.developers.DeveloperPage;
import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.admins.sellers.*;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import java.util.ArrayList;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.Scanner.*;

public class AdminProfile {

    private DataBase dataBase;

    public AdminProfile(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void profile(int index, ArrayList<Admin> admins, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( " + typeOfAdmin + " PROFILE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                showAdmins(index, admins);
                System.out.println("Do you want to change your information?");
                System.out.println("1)Yes");
                System.out.println("2)No");
                String changeOrNo = scanString();
                if (changeOrNo.equals("1")) {
                    changeItems(index, admins, typeOfAdmin);
                } else if (changeOrNo.equals("2")) {
                    profile(index, admins, typeOfAdmin);
                } else {
                    incorrect();
                    profile(index, admins, typeOfAdmin);
                }
            }
            case "2" -> goBack(index, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                profile(index, admins, typeOfAdmin);
            }
        }
    }

    public void goBack(int index, String typeOfAdmin) {
        if (typeOfAdmin.equals("MANAGER")) {
            ManagerPage managerPage = new ManagerPage(dataBase);
            managerPage.goToManagerPage(index);
        } else if (typeOfAdmin.equals("DEVELOPER")) {
            DeveloperPage developerPage = new DeveloperPage(dataBase);
            developerPage.goToDeveloperPage(index);
        } else {
            SellerPage sellerPage = new SellerPage(dataBase);
            sellerPage.goToSellerPage(index);
        }
    }

    public void changeItems(int index, ArrayList<Admin> admins, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE PROFILE ITEMS )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> changeWhat(index, admins, typeOfAdmin);
            case "2" -> profile(index, admins, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                changeItems(index, admins, typeOfAdmin);
            }
        }
    }

    public void changeWhat(int index, ArrayList<Admin> admins, String typeOfAdmin) {
        switch (whichItem()) {
            case "1" -> {
                System.out.println("Enter new username:");
                String newUsername = scanString();
                while (duplicateUsername(newUsername, index) == 1) {
                    System.out.println(ConsoleColors.RED + "Duplicate username!Enter another one!" + ConsoleColors.RESET);
                    System.out.println("Enter another username:");
                    newUsername = scanString();
                }
                admins.get(index).setUserName(newUsername);
                changedSuccessfully();
            }
            case "2" -> {
                System.out.println("Enter new password:");
                String newPassword = scanString();
                while (checkPassword(newPassword) == 1) {
                    System.out.println("Enter another password:");
                    newPassword = scanString();
                }
                admins.get(index).setPassword(newPassword);
                changedSuccessfully();
            }
            case "3" -> {
                System.out.println("Enter new phone number:");
                admins.get(index).setPhoneNumber(scanString());
                changedSuccessfully();
            }
            case "4" -> {
                System.out.println("Enter new Email:");
                admins.get(index).setEmail(scanString());
                changedSuccessfully();
            }
            default -> {
                incorrect();
            }
        }
        DaoWriter.writeData(dataBase);
        changeItems(index, admins, typeOfAdmin);
    }

    public String whichItem() {
        System.out.println("Which item do want to change?");
        System.out.println("1)Username");
        System.out.println("2)Password");
        System.out.println("3)Phone number");
        System.out.println("4)Email");
        return scanString();
    }

    public int duplicateUsername(String username, int userIndex) {
        for (int i = 0; i < dataBase.getManagers().size(); i++) {
            if (dataBase.getManagers().get(i).getUserName().equals(username) && i != userIndex) {
                return 1;
            }
        }
        for (int i = 0; i < dataBase.getDevelopers().size(); i++) {
            if (dataBase.getDevelopers().get(i).getUserName().equals(username) && i != userIndex) {
                return 1;
            }
        }
        for (int i = 0; i < dataBase.getSellers().size(); i++) {
            if (dataBase.getSellers().get(i).getUserName().equals(username) && i != userIndex) {
                return 1;
            }
        }
        return 0;
    }

    public int checkPassword(String password) {
        if (!password.matches(".*" + "[A-Z]+" + ".*") ||
                !password.matches(".*" + "[a-z]+" + ".*") ||
                !password.matches(".*" + "[0-9]+" + ".*")) {
            System.out.println("Password must include at least 1 numbers , 1 capital and \n" +
                    "1 small letter!");
            return 1;
        } else {
            if (password.length() < 8) {
                System.out.println("Password must include at least 8 letters!");
                return 1;
            } else {
                return 0;
            }
        }
    }
}