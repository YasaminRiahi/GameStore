package ir.ac.kntu.admins.managers;

import ir.ac.kntu.helpers.ConsoleColors;
import ir.ac.kntu.products.games.Games;
import ir.ac.kntu.store.DataBase;

import java.util.Collections;

import static ir.ac.kntu.helpers.Scanner.scanString;
import static ir.ac.kntu.helpers.TextTypings.*;
import static ir.ac.kntu.helpers.TextTypings.incorrect;

public class GameCrashReport {

    private DataBase dataBase;

    public GameCrashReport(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void gameReport(int whichManager) {
        drawingLines();
        System.out.println(ConsoleColors.BLUE_BOLD + "******( REPORT PAGE )******" + ConsoleColors.RESET);
        String nextChoose = whereToGo();
        switch (nextChoose) {
            case "1" -> {
                showGames();
                sendMassage(chooseGame());
                gameReport(whichManager);
            }
            case "2" -> {
                ManagerGamePage managerGamePage = new ManagerGamePage(dataBase);
                managerGamePage.gamesPage(whichManager);
            }
            case "3" -> {
                drawingLines();
                exit();
            }
            default -> {
                incorrect();
                gameReport(whichManager);
            }
        }
    }

    public void showGames() {
        int j = 1;
        for (int i = 0; i < dataBase.getGames().size(); i++) {
            System.out.println(j + ")" + dataBase.getGames().get(i).getName());
            j++;
        }
    }

    public Games chooseGame() {
        System.out.println("Choose one of the games:");
        int gameIndex = Integer.parseInt(scanString()) - 1;
        return dataBase.getGames().get(gameIndex);
    }

    public void sendMassage(Games game) {
        Collections.sort(game.getDevelopers());
        game.getDevelopers().get(0).getInbox().add(game);
        System.out.println("Massage sent successfully!");
    }
}