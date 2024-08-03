package ir.ac.kntu.admins.commonInGames;

import ir.ac.kntu.admins.developers.DeveloperGamePage;
import ir.ac.kntu.admins.managers.ManagerGamePage;
import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.store.DaoWriter;
import ir.ac.kntu.store.DataBase;

import static ir.ac.kntu.helpers.Scanner.scanGames;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class AddingAGame {

    private DataBase dataBase;

    public AddingAGame(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void toAddGames(int whichUser, String typeOfAdmin) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( ADD GAMES )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                if (typeOfAdmin.equals("MANAGER")) {
                    dataBase.addGames(scanGames());
                    DaoWriter.writeData(dataBase);
                    ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
                    managerGamePage.gamesPage(whichUser);
                } else {
                    dataBase.addGames(scanGames());
                    DaoWriter.writeData(dataBase);
                    dataBase.getGames().get(dataBase.getGames().size() - 1).addDeveloper(dataBase.getDevelopers().get(whichUser));
                    dataBase.getDevelopers().get(whichUser).getDeveloperGame().
                            add(dataBase.getGames().get(dataBase.getGames().size() - 1));
                }
                toAddGames(whichUser, typeOfAdmin);
            }
            case "2" -> goBack(whichUser, typeOfAdmin);
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                toAddGames(whichUser, typeOfAdmin);
            }
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