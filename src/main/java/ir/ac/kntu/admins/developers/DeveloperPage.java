package ir.ac.kntu.admins.developers;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.admins.AdminProfile;
import ir.ac.kntu.admins.commonInAccessories.AccessoriesPage;
import ir.ac.kntu.admins.sellers.SellerOptions;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class DeveloperPage {

    private DataBase dataBase;

    private DeveloperOptions developerOptions;

    public DeveloperPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToDeveloperPage(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( DEVELOPER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showDeveloperOptions(whichDeveloper);
        } else if (nextChoose.equals("2")) {
            AdminPage adminPage = new AdminPage(dataBase);
            adminPage.goToAdminPage();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            goToDeveloperPage(whichDeveloper);
        }
    }

    public void showDeveloperOptions(int whichDeveloper) {
        for (DeveloperOptions developerOptions1 : DeveloperOptions.values()) {
            System.out.print(developerOptions1.getValue() + ")");
            System.out.println(developerOptions1);
        }
        fromValue(getNumberFromOptions(), whichDeveloper);
    }

    public void fromValue(String value, int whichDeveloper) {
        for (DeveloperOptions e : DeveloperOptions.values()) {
            if (e.getValue().equals(value)) {
                this.developerOptions = e;
                goToOptions(whichDeveloper);
            }
        }
        incorrect();
        showDeveloperOptions(whichDeveloper);
    }

    public void goToOptions(int whichDeveloper) {
        if (developerOptions == DeveloperOptions.PROFILE) {
            AdminProfile adminProfile = new AdminProfile(dataBase);
            adminProfile.profile(whichDeveloper, dataBase.getDevelopers(), "DEVELOPER");
        } else if (developerOptions == DeveloperOptions.GAMES) {
            DeveloperGamePage developerGamePage = new DeveloperGamePage(dataBase);
            developerGamePage.gamesPage(whichDeveloper);
        } else if (developerOptions == DeveloperOptions.CHECK_INBOX) {
            ;
        } else if (developerOptions == DeveloperOptions.SCHEDULED_EVENTS) {
            ;
        } else if (developerOptions == DeveloperOptions.SCHEDULED_EVENTS) {
            ;
        } else {
            ;
        }
    }
}
