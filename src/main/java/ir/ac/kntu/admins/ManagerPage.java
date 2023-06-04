package ir.ac.kntu.admins;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.menu.MainMenu;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ManagerPage {

    private DataBase dataBase;

    public ManagerPage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void goToManagerPage(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MANAGER PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            ;
        } else if (nextChoose.equals("2")) {
            ;
        } else if (nextChoose.equals("3")) {
            ;
        } else {
            ;
        }
    }
}
