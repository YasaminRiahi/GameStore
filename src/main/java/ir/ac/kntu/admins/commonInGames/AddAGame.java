package ir.ac.kntu.admins.commonInGames;

import ir.ac.kntu.admins.developers.DeveloperGamePage;
import ir.ac.kntu.admins.developers.DeveloperPage;
import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.admins.managers.ManagerUserPage;
import ir.ac.kntu.admins.sellers.SellerPage;
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
                dataBase.getGames().get(dataBase.getGames().size()-1).addDeveloper(dataBase.getDevelopers().get(whichUser));
            }
            toAddGames(whichUser,typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            goBack(whichUser,typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            toAddGames(whichUser, typeOfAdmin);
        }
    }

    public void goBack(int whichUser, String typeOfAdmin) {
        if (typeOfAdmin.equals("MANAGER")) {
            ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
            managerGamePage.gamesPage(whichUser);
        } else if (typeOfAdmin.equals("DEVELOPER")) {
            DeveloperGamePage developerGamePage = new DeveloperGamePage(dataBase);
            developerGamePage.gamesPage(whichUser);
        }
    }
}
