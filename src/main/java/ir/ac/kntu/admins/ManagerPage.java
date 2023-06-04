package ir.ac.kntu.admins;

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
        if (nextChoose.equals("1")) {
            showManagerOptions(whichManager);
        } else if (nextChoose.equals("2")) {
            AdminPage adminPage = new AdminPage(dataBase);
            adminPage.goToAdminPage();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            goToManagerPage(whichManager);
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
            ;
        } else if (managerOptions == ManagerOptions.GAMES) {
            ;
        } else {
            ;
        }
    }
}