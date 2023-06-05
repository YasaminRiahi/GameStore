package ir.ac.kntu.admins;

import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.scanGames;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AddAGame {

    private DataBase dataBase;

    public AddAGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void toAddGames(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD GAMES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            if (typeOfAdmin.equals("MANAGER")) {
                dataBase.addGames(scanGames());
                ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
                managerGamePage.gamesPage(whichUser);
            } else {
                dataBase.addGames(scanGames());
                dataBase.addDevelopers(dataBase.getDevelopers().get(whichUser));

            }

        } else if (nextChoose.equals("2")) {
            ManagerUserPage managerUserPage = new ManagerUserPage(dataBase);
            managerUserPage.usersPage(whichUser);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            toAddGames(whichUser, typeOfAdmin);
        }
    }
}
