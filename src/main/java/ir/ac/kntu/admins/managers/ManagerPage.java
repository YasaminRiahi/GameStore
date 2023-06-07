package ir.ac.kntu.admins.managers;

import ir.ac.kntu.admins.commonInAccessories.AccessoriesPage;
import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ManagerPage {

    private DataBase dataBase;

    private ManagerOptions managerOptions;

    public ManagerPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToManagerPage(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MANAGER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> showManagerOptions(whichManager);
            case "2" -> {
                AdminPage adminPage = new AdminPage(dataBase);
                adminPage.goToAdminPage();
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                goToManagerPage(whichManager);
            }
        }
    }

    public void showManagerOptions(int whichManager) {
        for (ManagerOptions managerOptions1 : ManagerOptions.values()) {
            System.out.print(managerOptions1.getValue() + ")");
            System.out.println(managerOptions1);
        }
        fromValue(getNumberFromOptions(), whichManager);
    }

    public void fromValue(String value, int whichManager) {
        for (ManagerOptions e : ManagerOptions.values()) {
            if (e.getValue().equals(value)) {
                this.managerOptions = e;
                goToOptions(whichManager);
            }
        }
        incorrect();
        showManagerOptions(whichManager);
    }

    public void goToOptions(int whichManager) {
        if (managerOptions == ManagerOptions.PROFILE) {
            AdminProfile adminProfile = new AdminProfile(dataBase);
            adminProfile.profile(whichManager, dataBase.getManagers(), "MANAGER");
        } else if (managerOptions == ManagerOptions.USERS) {
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichManager);
        } else if (managerOptions == ManagerOptions.GAMES) {
            ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
            managerGamePage.gamesPage(whichManager);
        } else {
            AccessoriesPage accessoriesPage = new AccessoriesPage(dataBase);
            accessoriesPage.goToAccessoriesPage(whichManager, "MANAGER");
        }
    }
}