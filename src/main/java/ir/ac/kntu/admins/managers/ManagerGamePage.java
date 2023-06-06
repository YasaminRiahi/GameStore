package ir.ac.kntu.admins.managers;

import ir.ac.kntu.admins.AddAGame;
import ir.ac.kntu.admins.ChangeGameInformation;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class ManagerGamePage {

    private DataBase dataBase;

    private ManagerGamesOptions managerGamesOption;

    public ManagerGamePage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void gamesPage(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( MANAGERS GAMES PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showManagerGamesOptions(whichManager);
        } else if (nextChoose.equals("2")) {
            ManagerPage managerPage = new ManagerPage(dataBase);
            managerPage.goToManagerPage(whichManager);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            gamesPage(whichManager);
        }
    }

    public void showManagerGamesOptions(int whichManager) {
        for (ManagerGamesOptions managerUsersOption1 : ManagerGamesOptions.values()) {
            System.out.print(managerUsersOption1.getValue() + ")");
            System.out.println(managerUsersOption1);
        }
        fromValue(getNumberFromOptions(), whichManager);
    }

    public void fromValue(String value, int whichManager) {
        for (ManagerGamesOptions e : ManagerGamesOptions.values()) {
            if (e.getValue().equals(value)) {
                this.managerGamesOption = e;
                goToOptions(whichManager);
            }
        }
        incorrect();
        showManagerGamesOptions(whichManager);
    }

    public void goToOptions(int whichManager) {
        if (managerGamesOption == ManagerGamesOptions.ADD_A_GAME) {
            AddAGame addAGame = new AddAGame(dataBase);
            addAGame.toAddGames(whichManager,"MANAGER");
        } else if (managerGamesOption == ManagerGamesOptions.CHANGE_GAMES_INFORMATION) {
            ChangeGameInformation changeGameInformation = new ChangeGameInformation(dataBase);
            changeGameInformation.changeGamesInformation(whichManager,"MANAGER");
        } else if (managerGamesOption == ManagerGamesOptions.REMOVE_A_GAME) {
            ;
        } else {
            ;
        }
    }
}
