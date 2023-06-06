package ir.ac.kntu.admins;

import ir.ac.kntu.admins.developers.DeveloperPage;
import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.admins.managers.ManagerGamesOptions;
import ir.ac.kntu.admins.managers.ManagerPage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.TextTypings.*;

public class ChangeGameInformation {

    private DataBase dataBase;

    private EditingGameOptions editingGameOptions;

    public ChangeGameInformation(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void changeGamesInformation(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( CHANGE GAMES INFORMATION )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        if (nextChoose.equals("1")) {
            showEditingGamesOptions(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("2")) {
            goBack(whichUser, typeOfAdmin);
        } else if (nextChoose.equals("3")) {
            System.out.println("Finish!");
            drawingLines();
            exit();
        } else {
            incorrect();
            changeGamesInformation(whichUser, typeOfAdmin);
        }

    }

    public void showEditingGamesOptions(int whichUser, String typeOfAdmin) {
        for (EditingGameOptions editingGameOptions1 : EditingGameOptions.values()) {
            System.out.print(editingGameOptions1.getValue() + ")");
            System.out.println(editingGameOptions1);
        }
        fromValue(getNumberFromOptions(), whichUser, typeOfAdmin);
    }

    public void fromValue(String value, int whichUser, String typeOfAdmin) {
        for (EditingGameOptions e : EditingGameOptions.values()) {
            if (e.getValue().equals(value)) {
                this.editingGameOptions = e;
                goToOptions(whichUser, typeOfAdmin);
            }
        }
        incorrect();
        showEditingGamesOptions(whichUser, typeOfAdmin);
    }

    public void goToOptions(int whichManager, String typeOfAdmin) {
        if (editingGameOptions == EditingGameOptions.BY_LIST_OF_GAMES) {
            ToEditGame toEditGame = new ToEditGame(dataBase);
            toEditGame.changeByList(whichManager, typeOfAdmin);
        } else {
            ;
        }
    }

    public void goBack(int whichUser, String typeOfAdmin) {
        if (typeOfAdmin.equals("MANAGER")) {
            ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
            managerGamePage.gamesPage(whichUser);
        } else if (typeOfAdmin.equals("DEVELOPER")) {
            ;
        }
    }

    public void toChange(int gameIndex) {
        String which = scanString();
        switch (Integer.parseInt(which)) {
            case 1:
                toChangeWhat("name");
                dataBase.getGames().get(gameIndex).setName(scanString());
                break;
            case 2:
                toChangeWhat("genre");
                dataBase.getGames().get(gameIndex).setGenre(scanString());
                break;
            case 3:
                toChangeWhat("description");
                dataBase.getGames().get(gameIndex).setDescription(scanString());
                break;
            case 4:
                toChangeWhat("rating");
                double rating = scanDouble();
                dataBase.getGames().get(gameIndex).setRating(rating);
                dataBase.getGames().get(gameIndex).setBeginningRate(rating);
                break;
            case 5:
                toChangeWhat("number of rates");
                int numberOfRates = scanInt();
                dataBase.getGames().get(gameIndex).setNumberOfRates(numberOfRates);
                dataBase.getGames().get(gameIndex).setBeginningNumber(numberOfRates);
                break;
            case 6:
                toChangeWhat("cost");
                dataBase.getGames().get(gameIndex).setCost(scanDouble());
                break;
            default:
                incorrect();
                toChange(gameIndex);
        }
    }
}
