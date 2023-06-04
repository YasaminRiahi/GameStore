package ir.ac.kntu.admins;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class SellerPage {

    private DataBase dataBase;

    public SellerPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToSellerPage(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( SELLER PAGE )******" + ConsoleColors.RESET);
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
            goToSellerPage(whichManager);
        }
    }
}
