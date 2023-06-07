package ir.ac.kntu.admins.developers;

import ir.ac.kntu.admins.commonInGames.AddingAGame;
import ir.ac.kntu.admins.commonInGames.ChangingGameInformation;
import ir.ac.kntu.admins.commonInGames.RemovingGame;
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
        switch (nextChoose) {
            case "1" -> showDeveloperGamesOptions(whichDeveloper);
            case "2" -> {
                DeveloperPage developerPage = new DeveloperPage(dataBase);
                developerPage.goToDeveloperPage(whichDeveloper);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                gamesPage(whichDeveloper);
            }
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
            AddingAGame addAGame = new AddingAGame(dataBase);
            addAGame.toAddGames(whichDeveloper, "DEVELOPER");
        } else if (developerGamesOptions == DeveloperGamesOptions.CHANGE_GAMES_INFORMATION) {
            ChangingGameInformation changeGameInformation = new ChangingGameInformation(dataBase);
            changeGameInformation.changeGamesInformation(whichDeveloper, "DEVELOPER");
        } else {
            RemovingGame removeGame = new RemovingGame(dataBase);
            removeGame.removeGames(whichDeveloper, "DEVELOPER");
        }
    }
}