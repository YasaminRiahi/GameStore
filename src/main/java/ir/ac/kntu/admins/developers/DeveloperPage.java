package ir.ac.kntu.admins.developers;

import ir.ac.kntu.admins.AdminPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class DeveloperPage {

    private DataBase dataBase;

    public DeveloperPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToDeveloperPage(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( DEVELOPER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            ;
        } else if (nextChoose.equals("2")) {
            AdminPage adminPage = new AdminPage(dataBase);
            adminPage.goToAdminPage();
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            goToDeveloperPage(whichManager);
        }
    }
}
