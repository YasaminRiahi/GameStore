package ir.ac.kntu.admins;

import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scan.*;
import static ir.ac.kntu.helpers.Scan.scanDouble;
import static ir.ac.kntu.helpers.TextTypings.*;

public class RemoveGame {

    private DataBase dataBase;

    private EditingGameOptions editingGameOptions;

    public RemoveGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void removeGames(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REMOVE GAMES )******" + ConsoleColors.RESET);
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
            removeGames(whichUser, typeOfAdmin);
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
            ;
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


}
