package ir.ac.kntu.admins.developers;

import ir.ac.kntu.admins.commonInGames.AddAGame;
import ir.ac.kntu.admins.commonInGames.ChangeGameInformation;
import ir.ac.kntu.admins.commonInGames.RemoveGame;
import ir.ac.kntu.admins.managers.ManagerGamesOptions;
import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class DeveloperGamePage {

    private DataBase dataBase;

    private DeveloperGamesOptions developerGamesOptions;

    public DeveloperGamePage(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void gamesPage(int whichDeveloper) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( DEVELOPER GAMES PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showDeveloperGamesOptions(whichDeveloper);
        } else if (nextChoose.equals("2")) {
            DeveloperPage developerPage = new DeveloperPage(dataBase);
            developerPage.goToDeveloperPage(whichDeveloper);
        } else if (nextChoose.equals("3")) {
            drawingLines();
            exit();
        } else {
            incorrect();
            gamesPage(whichDeveloper);
        }
    }

    public void showDeveloperGamesOptions(int whichDeveloper) {
        for (DeveloperGamesOptions developerGamesOptions1 : DeveloperGamesOptions.values()) {
            System.out.print(developerGamesOptions1.getValue() + ")");
            System.out.println(developerGamesOptions1);
        }
        fromValue(getNumberFromOptions(), whichDeveloper);
    }

    public void fromValue(String value, int whichDeveloper) {
        for (DeveloperGamesOptions e : DeveloperGamesOptions.values()) {
            if (e.getValue().equals(value)) {
                this.developerGamesOptions = e;
                goToOptions(whichDeveloper);
            }
        }
        incorrect();
        showDeveloperGamesOptions(whichDeveloper);
    }

    public void goToOptions(int whichDeveloper) {
        if (developerGamesOptions == DeveloperGamesOptions.ADD_A_GAME) {
            AddAGame addAGame = new AddAGame(dataBase);
            addAGame.toAddGames(whichDeveloper,"DEVELOPER");
        } else if (developerGamesOptions == DeveloperGamesOptions.CHANGE_GAMES_INFORMATION) {
            ChangeGameInformation changeGameInformation = new ChangeGameInformation(dataBase);
            changeGameInformation.changeGamesInformation(whichDeveloper,"DEVELOPER");
        } else {
            RemoveGame removeGame =  new RemoveGame(dataBase);
            removeGame.removeGames(whichDeveloper,"DEVELOPER");
        }
    }
}
